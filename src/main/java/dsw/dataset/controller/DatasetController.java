package dsw.dataset.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.base.RestPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsw.dataset.data.Dataset;
import dsw.dataset.service.IDatasetService;



@RestController
public class DatasetController {

	@Autowired
	private IDatasetService datasetService;
	
	@GetMapping("/datasets")
	public RestPageResult<?> fetchDatasetList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", "superadmin");
		PagedList<Dataset> result = datasetService.getDatasetList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
	
    @PostMapping("/datasets")
	@ResponseBody
	public Map<String, Object> addDateset(@RequestParam(required = false) String userId, @RequestParam(required = false) String name, @RequestParam(defaultValue = "")  String description, 
			@RequestParam(defaultValue = "") String size, @RequestParam(defaultValue = "") String type) {
		
		Map<String, String> param = new HashMap<String,String>();
		param.put("userId", "superadmin");
		param.put("name", "假数据集");
		param.put("description",description);
		param.put("size", "12K");
		param.put("type", "data");
		Map<String, Object> resultMap = datasetService.addDateset(param);
		return resultMap;
	}
    
    @PostMapping("/uploadDataset")
	@ResponseBody
	public Map<String, Object> uploadDateset(@RequestParam(required = false) String file) {
		
    	Map<String,Object> resultMap=new LinkedHashMap<String,Object>();
    	resultMap.put("result", "success");
		return resultMap;
	}

	
	@PostMapping("/deleteDateset")
	@ResponseBody
	public Map<String, String> deleteDateset(String id) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", datasetService.deleteDateset(id));
		return resultMap;
	}
	
	@PostMapping("/updateDateset")
	@ResponseBody
	public Map<String, String> updateDateset(@RequestBody Map<String,Object> param) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", datasetService.updateDateset(param));
		return resultMap;
	}
	
	@PostMapping(value="/dataset/addToProject")
	@ResponseBody
	public Map<String,Object> addToProject(@RequestBody Map<String,Object> param){
		Map<String,Object> resultMap=new LinkedHashMap<String,Object>();
		
		param.put("userId", "superadmin");
		resultMap=datasetService.addToProject(param);
		return resultMap;
	}
	
	@GetMapping("/dataset/getAddedProjects")
	public RestPageResult<?> getAddedProjects(@RequestParam(required = false) String id,
	@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		PagedList<?> result = datasetService.getProjectList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
}
