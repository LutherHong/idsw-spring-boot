package dsw.trainjob.controller;

import java.util.HashMap;
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

import dsw.trainjob.data.PresetAlgorithm;
import dsw.trainjob.service.IPresetAlgorithmService;
import dsw.trainjob.service.ITrainJobService;

@RestController
public class TrainJobController {
	
	@Autowired
	private ITrainJobService trainJobService;
	
	@Autowired
	private IPresetAlgorithmService presetAlgorithmService;
	
	
	@GetMapping("/trainjobs")
	public RestPageResult<?> getList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "superadmin");
		PagedList<?> result = trainJobService.getList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
	
	@PostMapping("/deleteTrainJob")
	@ResponseBody
	public Map<String, String> deleteTrainJob(@RequestParam(required = false) String id){
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", trainJobService.deleteTrainJob(id));
		return resultMap;
		
	}
	
	@PostMapping("/updateTrainJob")
	@ResponseBody
	public String updateTrainJob(@RequestBody Map<String,Object> param){
		return trainJobService.updateTrainJob(param);
	}
	
	@GetMapping("/getAlgoList")
	@ResponseBody
	public RestPageResult<?> getPresetAlgorithmList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "superadmin");
		PagedList<PresetAlgorithm> result = presetAlgorithmService.queryByParam(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	}
	
	/*@ResponseBody
	@GetMapping("/trainjobs")
	public TrainJob getTrainJob(@RequestParam(required = false) String id){
		return trainJobService.getTrainJob(id);
	}
	
	@PostMapping("/addTrainJob")
	@ResponseBody
	public void addTrainJob(@RequestBody Map<String,Object> param){
		String userId= "superadmin";
		param.put("userId",userId);
		trainJobService.addTrainJob(param);
	}
	
	@PostMapping("/updateTrainJob")
	@ResponseBody
	public void updateTrainJob(@RequestBody Map<String,Object> param){
		trainJobService.updateTrainJob(param);
	}
	
	@PostMapping("/remvoeTrainJob")
	@ResponseBody
	public void remvoeTrainJob(@RequestParam(required = false) String id){
		trainJobService.removeTrainJob(id);
	}
	
	
*/
}
