package dsw.project.service.impl;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.dataset.dao.DataSetMapper;
import dsw.dataset.data.Dataset;
import dsw.deployment.dao.DeployedMapper;
import dsw.deployment.data.Deployed;
import dsw.experiment.dao.ExperimentMapper;
import dsw.experiment.data.Experiment;
import dsw.model.dao.ModelMapper;
import dsw.model.data.Model;
import dsw.notebook.dao.NoteBookMapper;
import dsw.notebook.data.NoteBook;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import dsw.project.dao.ProjectAssetMapper;
import dsw.project.dao.ProjectMapper;
import dsw.project.data.Project;
import dsw.project.data.ProjectAsset;
import dsw.project.service.IProjectAssetService;


@Service("projectAssetService")
public class ProjectAssetServiceImpl implements IProjectAssetService {

	@Autowired
	private ProjectAssetMapper projectAssetMapper;
	@Autowired
	private DataSetMapper dataSetMapper;
	@Autowired
	private ExperimentMapper experimentMapper;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DeployedMapper deployedMapper;
	
	@Autowired
	private ProjectMapper projectMapper;

	
	@Autowired
	private NoteBookMapper notebookFileMapper;
	
	@Override
	public List<Dataset> getDataSetList(String projectId) {
		List<Dataset> dataSetList=new ArrayList<Dataset>();
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "DataSet");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		System.out.println("size为:"+paList.size());
		for(ProjectAsset pa: paList){
			String id=pa.getAssetId();
			Dataset ds=dataSetMapper.selectByPrimaryKey(id);
			System.out.println("Dataset为"+ds.getName());
			dataSetList.add(ds);
		}
		return dataSetList;
	}

	@Override
	public List<Model> getModelList(String projectId) {
		List<Model> modelList=new ArrayList<Model>();
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Model");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		for(ProjectAsset pa: paList){
			String id=pa.getAssetId();
			Model ds=modelMapper.selectByPrimaryKey(id);
			modelList.add(ds);
		}
		return modelList;
	}

	@Override
	public List<Experiment> getExperimentList(String projectId) {
		List<Experiment> experimentList=new ArrayList<Experiment>();
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Experiment");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		for(ProjectAsset pa: paList){
			String id=pa.getAssetId();
			Experiment experiment=experimentMapper.selectByPrimaryKey(id);
			experimentList.add(experiment);
		}
		return experimentList;
	}

	/**
	 * 获取Notebook列表
	 */
	@Override
	public List<NoteBook> getNotebookList(String projectId) {		
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Notebook");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		
		String userId = "superadmin";
		Example exampleNb = new Example(NoteBook.class);
		Criteria criteriaNb = exampleNb.createCriteria();
		criteriaNb.andEqualTo("userId", userId);
		List<NoteBook> notebookList=notebookFileMapper.selectByExample(exampleNb);
		
		List<String> nameList=paList.stream().map(ProjectAsset::getAssetId).collect(Collectors.toList());		
		notebookList=notebookList.stream().filter(notebook ->nameList.contains(notebook.getId())).collect(Collectors.toList());
		return notebookList;
	}

	@Override
	public List<Deployed> getDeployList(String projectId) {
		List<Deployed> deployList=new ArrayList<Deployed>();
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Deployment");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		
		for(ProjectAsset pa: paList){
			String id=pa.getAssetId();
			Deployed ds=deployedMapper.selectByPrimaryKey(id);
			deployList.add(ds);
		}
		return deployList;
	}
	
	@Override
	public void deleteAsset(String assetId,String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("assetId", assetId);
		criteria.andEqualTo("projectId", projectId);
		projectAssetMapper.deleteByExample(example);
	}

	@Override
	public int getCountOfDataSet(String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Dataset");
		int count=projectAssetMapper.selectCountByExample(example);
		return count;
	}

	@Override
	public int getCountOfModel(String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Model");
		int count=projectAssetMapper.selectCountByExample(example);
		return count;
	}

	@Override
	public int getCountOfExperiment(String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Experiment");
		int count=projectAssetMapper.selectCountByExample(example);
		return count;
	}

	@Override
	public int getCountOfNotebook(String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Notebook");
		int count=projectAssetMapper.selectCountByExample(example);
		return count;
	}

	@Override
	public int getCountOfDeploy(String projectId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("projectId", projectId);
		criteria.andEqualTo("assetType", "Deployment");
		int count=projectAssetMapper.selectCountByExample(example);
		return count;
	}

	@Override
	public List<Project> getProjectByExperiment(String expId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("assetId", expId);
		criteria.andEqualTo("assetType", "Experiment");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		List<Project> projectList=new ArrayList<Project>();
		for(ProjectAsset pa: paList){
			String projectId=pa.getProjectId();
			Project project=projectMapper.selectByPrimaryKey(projectId);
			projectList.add(project);
		}
		return projectList;
	}

	@Override
	public List<Project> getProjectByDataSet(String dataSetId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("assetId", dataSetId);
		criteria.andEqualTo("assetType", "DataSet");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		List<Project> projectList=new ArrayList<Project>();
		for(ProjectAsset pa: paList){
			String projectId=pa.getProjectId();
			Project project=projectMapper.selectByPrimaryKey(projectId);
			projectList.add(project);
		}
		return projectList;
	}
	
	@Override
	public List<Project> getProjectByModel(String modelId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("assetId", modelId);
		criteria.andEqualTo("assetType", "Model");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		List<Project> projectList=new ArrayList<Project>();
		for(ProjectAsset pa: paList){
			String projectId=pa.getProjectId();
			Project project=projectMapper.selectByPrimaryKey(projectId);
			projectList.add(project);
		}
		return projectList;
	}
	
	@Override
	public List<Project> getProjectByNotebook(String notebookId) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("assetId", notebookId);
		criteria.andEqualTo("assetType", "Notebook");
		List<ProjectAsset> paList=projectAssetMapper.selectByExample(example);
		List<Project> projectList=new ArrayList<Project>();
		for(ProjectAsset pa: paList){
			String projectId=pa.getProjectId();
			Project project=projectMapper.selectByPrimaryKey(projectId);
			projectList.add(project);
		}
		return projectList;
	}

}
