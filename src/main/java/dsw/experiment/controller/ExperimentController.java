package dsw.experiment.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

import dsw.experiment.service.IExperimentService;


@RestController
public class ExperimentController {

	@Autowired
	private IExperimentService experimentService;
	
	@GetMapping("/experiments")
	public RestPageResult<?> fetchExperimentList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", "superadmin");
		PagedList<?> result = experimentService.getExperimentList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
	
	@PostMapping("/experiments")
	@ResponseBody
	public Map<String, Object> addExperiment(@RequestParam(required = false)String userId, @RequestParam(required = false)String name, 
			@RequestParam(defaultValue = "") String description,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
		
		Map<String, String> param = new HashMap<String,String>();
		param.put("userId", userId);
		param.put("name", name);
		param.put("description", description);
		Map<String, Object> resultMap = experimentService.addExperiment(param, pageNum, pageSize);
		return resultMap;
	}
	
	@PostMapping("/deleteExperiment")
	@ResponseBody
	public Map<String, String> deleteExperiment(@RequestParam(required = false) String id) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", experimentService.deleteExperiment(id));
		return resultMap;
	}
	
	@PostMapping("/updateExperiment")
	@ResponseBody
	public Map<String, String> updateExperiment(@RequestBody Map<String,Object> param) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", experimentService.updateExperiment(param));
		return resultMap;
	}
	
	@PostMapping(value="/addToProject")
	public Map<String,Object> addToProject(@RequestParam String id, @RequestBody Map<String,Object> param){
		Map<String,Object> resultMap=new LinkedHashMap<String,Object>();
		param.put("expId", id);
		param.put("userId", "superadmin");
		resultMap=experimentService.addToProject(param);
		return resultMap;
	}
}
