package dsw.exampleUserInfo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.exampleUserInfo.dao.UserInfoMapper;
import dsw.exampleUserInfo.data.UserInfo;
import dsw.exampleUserInfo.service.IUserInfoService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public PagedList<UserInfo> getUserList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(UserInfo.class);
		Criteria criteria = example.createCriteria();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			if (key.toUpperCase().endsWith("@LIKE")) {
				key = key.substring(0, key.length() - 5);
				criteria.andLike(key, entry.getValue());
			} else {
				criteria.andEqualTo(key, entry.getValue());
			}
		}
		Page<UserInfo> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> userInfoMapper.selectByExample(example));
		return new PagedList<>(result.getResult(), result.getTotal());
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		return userInfo;
	}

	@Override
	public UserInfo addUserInfo(UserInfo userInfo) {
		userInfo.setCreateTime(DateUtil.getCurrentTime());
		userInfoMapper.insert(userInfo);
		return userInfo;
	}

	@Override
	public void removeUserInfo(String userId) {
		userInfoMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKey(userInfo);
	}


}
