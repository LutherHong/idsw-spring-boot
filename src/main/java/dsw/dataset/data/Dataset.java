package dsw.dataset.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dsw_dataset")
public class Dataset {
	@Id
	@Column(name = "data_id")
	private String id;
	@Column(name = "data_name")
	private String name;
	@Column(name = "data_path")
	private String path;
	@Column(name = "data_type")
	private String type;
	@Column(name = "data_size")
	private String size;
	@Column(name = "data_desc")
	private String description;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
