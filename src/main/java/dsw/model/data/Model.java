package dsw.model.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="dsw_model")
public class Model {

	@Id
	@Column(name="id")
	private String id;
	
	// 模型文件名称
	@Column(name="name")
	private String name;
	
	// 所属算法库(SparkML,TensorFlow,Scikit-Learn)
	@Column(name="kernel")
	private String kernel;
	
	// 文件存储路径
	@Column(name="path")
	private String path;
	
	// 模型描述
	@Column(name="description")
	private String description;
	
	// 项目ID
	@Column(name="project_id")
	private String projectId;
	
	// 项目名称
	@Column(name="project_name")
	@Transient
	private String projectName;
	
	// 所属用户
	@Column(name="user_id")
	private String userId;
	
	// 创建时间
	@Column(name="create_time")
	private String createTime;
	
	// 修改时间
	@Column(name="update_time")
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

	public String getKernel() {
		return kernel;
	}

	public void setKernel(String kernel) {
		this.kernel = kernel;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
