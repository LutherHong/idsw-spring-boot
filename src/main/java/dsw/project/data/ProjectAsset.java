package dsw.project.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="dsw_project_asset")
public class ProjectAsset {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "asset_type")
	private String assetType;
	
	@Column(name = "asset_id")
	private String assetId;
	
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
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
