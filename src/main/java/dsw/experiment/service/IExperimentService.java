package dsw.experiment.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;

public interface IExperimentService {

	PagedList<?> getExperimentList(Map<String, String> param, int pageNum, int pageSize);

	Map<String, Object> addExperiment(Map<String, String> param, int pageNum, int pageSize);

	String deleteExperiment(String id);

	String updateExperiment(Map<String, Object> param);

	Map<String, Object> addToProject(Map<String, Object> param);

}
