package dsw.deployment.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="dsw_model_deployed")
public class Deployed {
	// 主键
	@Id
	@Column(name="id")
	private String id;
	// 服务名称
	@Column(name="name")
	private String name;
	// notebook路径
	@Column(name="notebook_path")
	private String notebookPath;
	// 模型文件路径
	@Column(name="model_path")
	private String modelPath;
	// 描述
	@Column(name="description")
	private String description;
	// 容器ID
	@Column(name="container_id")
	private String containerId;
	// 开放端口
	@Column(name="port")
	private int port;
	// 基础镜像
	@Column(name="image_name")
	private String imageName;
/*	// 容器运行状态
	@Transient
	@Column(name="id")
	private String containerState;*/
	// 文件所有者
	@Column(name="user_id")
	private String userId;
	// 创建时间
	@Column(name="create_time")
	private String createTime;
	// 更新时间
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
	public String getNotebookPath() {
		return notebookPath;
	}
	public void setNotebookPath(String notebookPath) {
		this.notebookPath = notebookPath;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
