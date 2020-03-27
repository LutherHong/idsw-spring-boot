package dsw.experiment.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="dsw_experiment")
public class Experiment {
	@Id
	@Column(name = "exp_id")
	private String id;
	@Column(name = "exp_name")
	private String name;
	@Column(name = "exp_type")
	private String type;
	@Column(name = "exp_status")
	private String status="Draft";
	@Column(name = "exp_desc")
	private String description;
	@Column(name = "exp_graph")
	private String graph;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "update_time")
	private String updateTime ;
	@Column(name = "user_id")
	private String userId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
