package dsw.experiment.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.experiment.dao.ExperimentMapper;
import dsw.experiment.data.Experiment;
import dsw.experiment.service.IExperimentService;
import dsw.project.dao.ProjectAssetMapper;
import dsw.project.data.ProjectAsset;
import dsw.utils.DateTimeUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("experimentService")
public class ExperimentServiceImpl implements IExperimentService {
	@Autowired
	private ExperimentMapper experimentMapper;
	@Autowired
	private ProjectAssetMapper projectAssetMapper;

	@Override
	public PagedList<?> getExperimentList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(Experiment.class);
		Criteria criteria = example.createCriteria();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			if (key.toUpperCase().endsWith("@LIKE")) {
				key = key.substring(0, key.length() - 5);
				criteria.andLike(key, entry.getValue());
			} else {
				criteria.andEqualTo(key, entry.getValue());
				System.out.println(criteria);
			}
		}
		Page<Experiment> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> experimentMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
	}
	
	@Override
	public Map<String, Object> addExperiment(Map<String, String> param, int pageNum, int pageSize) {
		String id = UUIDGenerator.getUUID();
		Experiment exp = new Experiment();
		exp.setId(id);
		exp.setUserId(param.get("userId"));
		exp.setName(param.get("name"));
		exp.setDescription(param.get("description"));
		exp.setCreateTime(DateTimeUtil.getCurrentTime());
		exp.setUpdateTime(DateTimeUtil.getCurrentTime());
		
		// 执行数据库保存
		int i = experimentMapper.insert(exp);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", exp);
		resultMap.put("code", String.valueOf(i));
		return resultMap;
	}

	@Override
	public String deleteExperiment(String id) {
		Example example = new Example(Experiment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = experimentMapper.deleteByExample(example);
		return String.valueOf(i);
	}

	@Override
	public String updateExperiment(Map<String,Object> param) {
		Example example = new Example(Experiment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", (String)param.get("id"));
		
		Experiment exp = new Experiment();
		String id = UUIDGenerator.getUUID();
		exp.setId(id);
		exp.setUserId((String)param.get("userId"));
		exp.setName((String)param.get("name"));
		exp.setDescription((String)param.get("description"));
		exp.setCreateTime((String)DateTimeUtil.getCurrentTime());
		int i = experimentMapper.updateByExampleSelective(exp, example);
		return String.valueOf(i);
	}

	@Override
	public Map<String, Object> addToProject(Map<String, Object> param) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		String ids = (String) param.get("ids");
		String[] idArr = ids.split(",");
		/*一会儿打个断点看看过来的是什么，感觉可以是数组*/
		String expId = (String) param.get("expId");
		String userId = (String) param.get("userId");
		for (String id : idArr) {
			ProjectAsset pa = new ProjectAsset();
			String paId = UUIDGenerator.getUUID();
			pa.setId(paId);
			pa.setProjectId(id);
			pa.setAssetType("Experiment");
			pa.setAssetId(expId);
			pa.setUserId(userId);
			pa.setCreateTime(DateTimeUtil.getCurrentTime());
			projectAssetMapper.insert(pa);
		}
		resultMap.put("result", "success");
		return resultMap;
	}

}
