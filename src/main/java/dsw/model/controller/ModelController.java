package dsw.model.controller;

import java.util.HashMap;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.base.RestPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsw.model.data.Model;
import dsw.model.service.IModelService;

@RestController
public class ModelController {

	@Autowired
	private IModelService modelService;
	
	@GetMapping("/models")
	public RestPageResult<?> fetchModelList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
		Map<String, String> param = new HashMap<String, String>() ;
		param.put("userId","superadmin");
		PagedList<Model> result = modelService.getModelList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	} 
	
	@PostMapping("/deleteModel")
	@ResponseBody
	public Map<String, String> deleteExperiment(String id) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", modelService.deleteModel(id));
		return resultMap;
	}
}
