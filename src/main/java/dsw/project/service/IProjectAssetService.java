package dsw.project.service;

import java.util.List;

import dsw.dataset.data.Dataset;
import dsw.deployment.data.Deployed;
import dsw.experiment.data.Experiment;
import dsw.model.data.Model;
import dsw.notebook.data.NoteBook;
import dsw.project.data.Project;

public interface IProjectAssetService {
	
	public List<Dataset> getDataSetList(String projectId);
	public List<Model> getModelList(String projectId);
	public List<Experiment> getExperimentList(String projectId);
	public List<NoteBook> getNotebookList(String projectId);
	public List<Deployed> getDeployList(String projectId);
	
	public void deleteAsset(String assetId, String projectId);
	
	public int getCountOfDataSet(String projectId);
	public int getCountOfModel(String projectId);
	public int getCountOfExperiment(String projectId);
	public int getCountOfNotebook(String projectId);
	public int getCountOfDeploy(String projectId);
	
	public List<Project> getProjectByExperiment(String expId);
	public List<Project> getProjectByDataSet(String dataSetId);
	public List<Project> getProjectByModel(String modelId);
	public List<Project> getProjectByNotebook(String notebookId);
	
}
