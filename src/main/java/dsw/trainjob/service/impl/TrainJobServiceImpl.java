package dsw.trainjob.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.oozie.client.OozieClient;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.common.Constants;
import dsw.trainjob.dao.PresetAlgorithmMapper;
import dsw.trainjob.dao.TrainJobMapper;
import dsw.trainjob.data.PresetAlgorithm;
import dsw.trainjob.data.TrainJob;
import dsw.trainjob.service.ITrainJobService;
import dsw.utils.ConfigUtil;
import dsw.utils.DateTimeUtil;
import dsw.utils.HDFSUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("trainJobService")
public class TrainJobServiceImpl implements ITrainJobService {

	@Autowired
	private TrainJobMapper trainJobMapper;
	
	@Autowired
	private PresetAlgorithmMapper presetAlgorithmMapper;
	

	@Override
	public PagedList<TrainJob> getList(Map<String, Object> param, int pageNum, int pageSize) {
		Example example = new Example(TrainJob.class);
		Criteria criteria = example.createCriteria();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			String key = entry.getKey();
			if (key.toUpperCase().endsWith("@LIKE")) {
				key = key.substring(0, key.length() - 5);
				criteria.andLike(key, (String) entry.getValue());
			} else {
				criteria.andEqualTo(key, entry.getValue());
				System.out.println(criteria);
			}
		}
		Page<TrainJob> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> trainJobMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
	}
	
	@Override
	public String updateTrainJob(Map<String, Object> param) {
		Example example = new Example(TrainJob.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", (String)param.get("id"));
		
		TrainJob job = new TrainJob();
		String id = UUIDGenerator.getUUID();
		job.setId(id);
		job.setUserId((String)param.get("userId"));
		job.setName((String)param.get("name"));
		job.setDescription((String)param.get("description"));
		job.setUpdateTime((String)DateTimeUtil.getCurrentTime());
		int i = trainJobMapper.updateByExampleSelective(job, example);
		return String.valueOf(i);
	}
	
	
	@Override
	public TrainJob getTrainJob(String id) {
		return trainJobMapper.getTrainJob(id);
	}
	

	@Override
	public void addTrainJob(Map<String,Object> param){
		TrainJob trainJob=new TrainJob();
		String id=UUID.randomUUID().toString();
		trainJob.setId(id);
		String name=(String)param.get("name");
		trainJob.setName(name);
		String description=(String)param.get("description");
		trainJob.setDescription(description);
		String userId=(String)param.get("userId");
		trainJob.setUserId(userId);

		// workflow.xml
		Namespace wfNamespace = Namespace.getNamespace("uri:oozie:workflow:0.4");
		Element rootElement = new Element("workflow-app", wfNamespace);
		String wfName="trainjob-"+id.split("-")[0];
		rootElement.setAttribute("name", wfName);
		
		Element startElement = new Element("start");
		startElement.setNamespace(wfNamespace);
		startElement.setAttribute("to", "prepare-data");
		rootElement.addContent(startElement);
		
		Element prepareActionElement=new Element("action");
		prepareActionElement.setNamespace(wfNamespace);
		prepareActionElement.setAttribute("name","prepare-data");
				
		int algorithmId=Integer.valueOf((String)param.get("algorithmId"));
		PresetAlgorithm presetAlgorithm=presetAlgorithmMapper.getPresetAlgorithm(algorithmId);
		String dataSetPath=(String) param.get("dataSetPath");
		String trainOutputPath=(String)param.get("trainOutputPath");
		Map<String,String> prepareParam=new LinkedHashMap<String,String>();
		// 后期维护
		prepareParam.put("sh", "/data/models-master/prepare.sh");
		prepareParam.put("-r", presetAlgorithm.getPath());
		prepareParam.put("-i", dataSetPath);
		prepareParam.put("-o", "prepare_output");
				
		try {
			Element prepareShellElement=createOozieShellAction(prepareParam);
			prepareActionElement.addContent(prepareShellElement);
			Element prepareOkElement=new Element("ok");
			prepareOkElement.setNamespace(wfNamespace);
			prepareOkElement.setAttribute("to","train-model");
			prepareActionElement.addContent(prepareOkElement);
			Element prepareErrorElement=new Element("error");
			prepareErrorElement.setNamespace(wfNamespace);
			prepareErrorElement.setAttribute("to","fail");
			prepareActionElement.addContent(prepareErrorElement);
			rootElement.addContent(prepareActionElement);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		Element trainActionElement=new Element("action");
		trainActionElement.setNamespace(wfNamespace);
		trainActionElement.setAttribute("name","train-model");
		
		Map<String,String> trainParam=new LinkedHashMap<String,String>();
		trainParam.put("sh", "/data/models-master/train_inception_v3.sh");
		trainParam.put("-r", presetAlgorithm.getPath());
		trainParam.put("-d", "prepare_output");
		trainParam.put("-c", "True");
		trainParam.put("-t", trainOutputPath);
		trainParam.put("-k", "checkpoints");
		
				
		try {
			Element trainShellElement=createOozieShellAction(trainParam);
			trainActionElement.addContent(trainShellElement);
			Element trainOkElement=new Element("ok");
			trainOkElement.setNamespace(wfNamespace);
			trainOkElement.setAttribute("to","end");
			trainActionElement.addContent(trainOkElement);
			Element trainErrorElement=new Element("error");
			trainErrorElement.setNamespace(wfNamespace);
			trainErrorElement.setAttribute("to","fail");
			trainActionElement.addContent(trainErrorElement);
			rootElement.addContent(trainActionElement);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// kill
		Element killElement = new Element("kill");
		killElement.setNamespace(wfNamespace);
		killElement.setAttribute("name","fail");
		Element messageElement=new Element("message");
		messageElement.setNamespace(wfNamespace);
		messageElement.setText("Java failed, error message[${wf:errorMessage(wf:lastErrorNode())}]");
		killElement.addContent(messageElement);
		rootElement.addContent(killElement);
		
		// end
		Element endElement = new Element("end");
		endElement.setNamespace(wfNamespace);
		endElement.setAttribute("name","end");
		rootElement.addContent(endElement);
			
		Document document = new Document(rootElement);
		
		
		XMLOutputter xmloutputter = new XMLOutputter(Format.getPrettyFormat());
		OutputStream outputStream;
		String localRootPath=ConfigUtil.getString(Constants.TRAIN_JOB_TMP_DIR);
		String localWfPath=localRootPath.concat("/").concat(wfName);		
		File localDir = new File(localWfPath);
		if (!localDir.exists()) {
			localDir.mkdirs();
		} else {
			try {
				FileUtils.deleteDirectory(localDir);
				localDir.mkdirs();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String workflowXmlPath = localWfPath.concat("/workflow.xml") ;
		try {
			outputStream = new FileOutputStream(workflowXmlPath);
			xmloutputter.output(document, outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 上传Oozie应用
		StringBuffer sf = new StringBuffer();
		sf.append(ConfigUtil.getString(Constants.IDSW_HDFS_PATH));
		sf.append("/");
		sf.append(userId);
		sf.append("/trainjob/");
		sf.append(wfName);
		String hdfsPath = sf.toString();
		if (!HDFSUtil.exist(hdfsPath)) {
			HDFSUtil.makedir(hdfsPath);
		}
		try {
			HDFSUtil.uploadFile(hdfsPath+"/workflow.xml", new FileInputStream(new File(workflowXmlPath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 提交Oozie Job
		Properties props=null;
		try {
			props=loadProperties("oozie.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String oozieServer=(String) props.get("oozie.server");
	    OozieClient wc = new OozieClient(oozieServer);

	    Properties conf = wc.createConfiguration();
	    conf.setProperty(OozieClient.APP_PATH, hdfsPath);
	    conf.setProperty("user.name", "root");

	    String jobId="";
		try {
			jobId = wc.run(conf);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		trainJob.setExternalJobId(jobId);
		String createTime=DateTimeUtil.getCurrentTime();
		String updateTime=createTime;
		trainJob.setCreateTime(createTime);
		trainJob.setUpdateTime(updateTime);
		trainJobMapper.addTrainJob(trainJob);
	}


	@Override
	public String deleteTrainJob(String id) {
		Example example = new Example(TrainJob.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = trainJobMapper.deleteByExample(example);
		return String.valueOf(i);
	}
	
	
	
	private Element createOozieShellAction(Map<String,String> param) throws IOException {
		Properties props = loadProperties("job.properties");	
		
		Namespace shellNamespace=Namespace.getNamespace("uri:oozie:shell-action:0.2");
		Element shellElement=new Element("shell");
		shellElement.setNamespace(shellNamespace);

		Element jobTracker=new Element("job-tracker");
		jobTracker.setNamespace(shellNamespace);
		jobTracker.setText(props.getProperty("jobTracker"));
		shellElement.addContent(jobTracker);
		Element nameNode=new Element("name-node");
		nameNode.setNamespace(shellNamespace);
		nameNode.setText(props.getProperty("nameNode"));
		shellElement.addContent(nameNode);
		
		Element configuration=new Element("configuration");
		configuration.setNamespace(shellNamespace);
		Element property=new Element("property");
		property.setNamespace(shellNamespace);
		configuration.addContent(property);
		Element queueName=new Element("name");
		queueName.setNamespace(shellNamespace);
		queueName.setText("mapred.job.queue.name");
		property.addContent(queueName);
		Element queueValue=new Element("value");
		queueValue.setNamespace(shellNamespace);
		queueValue.setText(props.getProperty("queueName"));
		property.addContent(queueValue);
		shellElement.addContent(configuration);

		Element exec=new Element("exec");
		exec.setNamespace(shellNamespace);
		exec.setText("sh");
		shellElement.addContent(exec);
		
		Element executePathArgument=new Element("argument");
		executePathArgument.setNamespace(shellNamespace);
		executePathArgument.setText(param.remove("sh"));
		shellElement.addContent(executePathArgument);
		for(String key: param.keySet()) {
			Element keyArgument=new Element("argument");
			keyArgument.setNamespace(shellNamespace);
			keyArgument.setText(key);
			shellElement.addContent(keyArgument);
			
			Element valueArgument=new Element("argument");
			valueArgument.setNamespace(shellNamespace);
			valueArgument.setText(param.get(key));
			shellElement.addContent(valueArgument);
		}
	
		
		return shellElement;
	}

	private Properties loadProperties(String path) throws IOException {
		Properties props = new Properties();
		InputStream in=TrainJobServiceImpl.class.getClassLoader().getResourceAsStream(path);
		try {
			props.load(new BufferedReader(new InputStreamReader(in)));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(in!=null) {
				in.close();
			}
		}
		return props;
	}


}
