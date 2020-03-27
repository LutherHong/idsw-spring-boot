package dsw.notebook.controller;

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

import dsw.notebook.data.NoteBook;
import dsw.notebook.service.INoteBookService;


@RestController
public class NoteBookController {
	@Autowired
	private INoteBookService noteBookService;
	
	@GetMapping("/notebooks")
	public RestPageResult<?> fetchModelList(@RequestParam(required = false) String userId,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int limit) {
		Map<String, String> param = new HashMap<String, String>() ;
		param.put("userId","superadmin");
		PagedList<NoteBook> result = noteBookService.getNoteBookList(param, pageNum, limit);
		return RestPageResult.success(result.getData(), result.getTotal());
	} 
	
	@PostMapping("/notebooks")
	@ResponseBody
	public Map<String, Object> addNoteBook(String userId, String name, 
			@RequestParam(defaultValue = "") String kernel,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
		
		Map<String, String> param = new HashMap<String,String>();
		param.put("userId", userId);
		param.put("name", name);
		param.put("kernel", kernel);
		Map<String, Object> resultMap = noteBookService.addNoteBook(param, pageNum, pageSize);
		return resultMap;
	}
	
	@PostMapping("/deleteNoteBook")
	@ResponseBody
	public Map<String, String> deleteNoteBook(String id) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", noteBookService.deleteNoteBook(id));
		return resultMap;
	}
	
	@PostMapping("/updateNoteBook")
	@ResponseBody
	public Map<String, String> updateNoteBook(@RequestBody Map<String,Object> param) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("result", noteBookService.updateNoteBook(param));
		return resultMap;
	}
}
