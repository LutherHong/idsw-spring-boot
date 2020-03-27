package dsw.exampleUserInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.base.RestPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsw.exampleUserInfo.service.IUserInfoService;



@RestController
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoService;

/*	@GetMapping("/users")
	public RestPageResult<?> fetchUserList(@RequestParam(required = false) String userName,
			@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userName", userName);
		PagedList<UserInfo> result = userInfoService.getUserList(param, pageNum, pageSize);
		return RestPageResult.success(result.getData(), result.getTotal());
	}*/
}
