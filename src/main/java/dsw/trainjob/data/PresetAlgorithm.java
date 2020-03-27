package dsw.trainjob.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dsw_preset_algo")
public class PresetAlgorithm {
	@Id
	@Column(name = "algo_id")
	private int id;
	@Column(name = "algo_name")
	private String name;
	@Column(name = "algo_target")
	private String target;
	@Column(name = "algo_engine")
	private String engine;
	@Column(name = "algo_precision")
	private String precision;
	@Column(name = "algo_size")
	private String size;
	@Column(name = "algo_path")
	private String path;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "update_time")
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
		
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
