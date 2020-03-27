package dsw.trainjob.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dsw_train_job")
public class TrainJob {
	@Id
	@Column(name = "train_job_id")
	private String id;
	@Column(name = "train_job_name")
	private String name;
	@Column(name = "train_job_status")
	private String status;
	@Column(name = "train_job_desc")
	private String description;
	@Column(name = "external_job_id")
	private String externalJobId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "update_time")
	private String updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	public String getExternalJobId() {
		return externalJobId;
	}
	public void setExternalJobId(String externalJobId) {
		this.externalJobId = externalJobId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
