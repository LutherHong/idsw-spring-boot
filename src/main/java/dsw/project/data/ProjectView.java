package dsw.project.data;

import java.util.List;

import dsw.dataset.data.Dataset;
import dsw.deployment.data.Deployed;
import dsw.experiment.data.Experiment;
import dsw.model.data.Model;
import dsw.notebook.data.NoteBook;

public class ProjectView {
	private String id;
	private String name;
	private String description;
	private String createTime;
	private String updateTime;
	private String userId;
	
	private List<Dataset> dataSetList;
	private List<Experiment> experimentList;
	private List<NoteBook> notebookList;
	private List<Model> modelList;
	private List<Deployed> deployList;
	
	private int dataSetCount;
	private int experimentCount;
	private int notebookCount;
	private int modelCount;
	private int deployCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Dataset> getDataSetList() {
		return dataSetList;
	}
	public void setDataSetList(List<Dataset> dataSetList) {
		this.dataSetList = dataSetList;
	}
	public List<Experiment> getExperimentList() {
		return experimentList;
	}
	public void setExperimentList(List<Experiment> experimentList) {
		this.experimentList = experimentList;
	}
	public List<NoteBook> getNotebookList() {
		return notebookList;
	}
	public void setNotebookList(List<NoteBook> notebookList) {
		this.notebookList = notebookList;
	}
	public List<Model> getModelList() {
		return modelList;
	}
	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}
	public List<Deployed> getDeployList() {
		return deployList;
	}
	public void setDeployList(List<Deployed> deployList) {
		this.deployList = deployList;
	}
	public int getDataSetCount() {
		return dataSetCount;
	}
	public void setDataSetCount(int dataSetCount) {
		this.dataSetCount = dataSetCount;
	}
	public int getExperimentCount() {
		return experimentCount;
	}
	public void setExperimentCount(int experimentCount) {
		this.experimentCount = experimentCount;
	}
	public int getNotebookCount() {
		return notebookCount;
	}
	public void setNotebookCount(int notebookCount) {
		this.notebookCount = notebookCount;
	}
	public int getModelCount() {
		return modelCount;
	}
	public void setModelCount(int modelCount) {
		this.modelCount = modelCount;
	}
	public int getDeployCount() {
		return deployCount;
	}
	public void setDeployCount(int deployCount) {
		this.deployCount = deployCount;
	}
	
	
	
}
