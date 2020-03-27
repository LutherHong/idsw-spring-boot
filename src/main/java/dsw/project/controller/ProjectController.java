package dsw.project.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.base.RestPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsw.project.data.ProjectView;
import dsw.project.service.IProjectService;



@RestController
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@GetMapping("/projects")
	public RestPageResult<?> fectchProjectList(@RequestParam(required = false) String ownerId,
		@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ownerId", "superadmin");
			PagedList<?> result = projectService.getProjectList(param, pageNum, limit);
			return RestPageResult.success(result.getData(), result.getTotal());
		}
	
	@PostMapping("/projects")
	@ResponseBody
	public Map<String, Object> addProject(String ownerId, String name, 
			@RequestParam(defaultValue = "") String description,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
		
		Map<String, String> param = new HashMap<String,String>();
		param.put("ownerId", ownerId);
		param.put("name", name);
		param.put("description", description);
		//project.setOwnerId(userInfoService.getLoginUserId());
		Map<String, Object> resultMap = projectService.addProject(param, pageNum, pageSize);
		return resultMap;
	}
	
	@PostMapping("/deleteProject")
	@ResponseBody
	public Map<String, String> deleteProject(String id) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", projectService.deleteProject(id));
		return resultMap;
	}
	
	@PostMapping("/updateProject")
	@ResponseBody
	public Map<String, String> updateProject(@RequestBody Map<String,Object> param) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", projectService.updateProject(param));
		return resultMap;
	}
	
	
	@RequestMapping(value = "/getProjectView", method = RequestMethod.GET)
	@ResponseBody
	public ProjectView getProject(@PathVariable("id") String id) {
		return projectService.getProjectView(id);
	}
	
	@RequestMapping(value = "/getProjectViewList", method = RequestMethod.GET)
	@ResponseBody
	public RestPageResult<?> pagingProjects(@RequestParam Map<String, Object> params) {
		params.put("ownerId", "superadmin");
		PagedList<?> result =projectService.getProjectViewList(params);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
}
