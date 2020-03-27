package dsw.exampleUserInfo.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;

import dsw.exampleUserInfo.data.UserInfo;

public interface IUserInfoService {

	PagedList<UserInfo> getUserList(Map<String, String> param, int pageNum, int pageSize);

	UserInfo getUserInfo(String userId);

	UserInfo addUserInfo(UserInfo userInfo);

	void removeUserInfo(String userId);

	void updateUserInfo(UserInfo userInfo);
	

}
