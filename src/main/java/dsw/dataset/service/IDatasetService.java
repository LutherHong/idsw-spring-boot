package dsw.dataset.service;

import java.util.Map;
import org.loushang.framework.base.PagedList;

import dsw.dataset.data.Dataset;


public interface IDatasetService {
	
	PagedList<Dataset> getDatasetList(Map<String, String> param, int pageNum, int pageSize);

	Map<String, Object> addDateset(Map<String, String> param);

	String deleteDateset(String id);

	String updateDateset(Map<String, Object> param);

	Map<String, Object> addToProject(Map<String, Object> param);

	PagedList<?> getProjectList(Map<String, String> param, int pageNum, int limit);


	
}
