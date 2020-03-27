package dsw.exampleUserInfo.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "demo_user")
public class UserInfo {
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "nick_name")
	private String nickName;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "update_time")
	private String updateTime;
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String currentTime) {
		this.createTime = currentTime;
		
	}
}
