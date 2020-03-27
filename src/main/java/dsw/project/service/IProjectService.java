package dsw.project.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.springframework.web.bind.annotation.RequestBody;

import dsw.project.data.ProjectView;

public interface IProjectService {

	PagedList<?> getProjectList(Map<String, String> param, int pageNum, int pageSize);

	Map<String, Object> addProject(Map<String, String> param, int pageNum, int pageSize);

	String deleteProject(String id);

	String updateProject(@RequestBody Map<String,Object> param);
	
	////////////////////View  ////////////////
	ProjectView getProjectView(String projectId);
	
	PagedList<?> getProjectViewList(Map<String,Object> params);
	
}
