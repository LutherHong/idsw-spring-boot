package dsw.project.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="dsw_project")
public class Project {
	
	@Id
	@Column(name="id")
	// 主键
	private String id;
	
	// 名称
	@Column(name="name")
	private String name;
	
	// 项目描述
	@Column(name="description")
	private String description;
	
	@Column(name="path")
	private String path;
	
	// 所属用户
	@Column(name="owner_id")
	private String ownerId;
	
	// 创建时间
	@Column(name="created_at")
	private String createdAt;
	
	// 修改时间
	@Column(name="updated_at")
	private String updatedAt;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
