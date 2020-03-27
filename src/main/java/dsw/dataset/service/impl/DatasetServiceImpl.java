package dsw.dataset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.dataset.dao.DataSetMapper;
import dsw.dataset.data.Dataset;
import dsw.dataset.service.IDatasetService;
import dsw.project.dao.ProjectAssetMapper;
import dsw.project.data.ProjectAsset;
import dsw.utils.DateTimeUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("datasetService")
public class DatasetServiceImpl implements IDatasetService {

	@Autowired
	private DataSetMapper datasetMapper;
	@Autowired
	private ProjectAssetMapper projectAssetMapper;
	
	@Override
	public PagedList<Dataset> getDatasetList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(Dataset.class);
		Criteria criteria = example.createCriteria();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			if (key.toUpperCase().endsWith("@LIKE")) {
				key = key.substring(0, key.length() - 5);
				criteria.andLike(key, entry.getValue());
			} else {
				criteria.andEqualTo(key, entry.getValue());
				System.out.println(criteria);
			}
		}
		Page<Dataset> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> datasetMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
		
	}
	@Override
	public Map<String, Object> addDateset(Map<String, String> param) {
		String id = UUIDGenerator.getUUID();
		Dataset dataset = new Dataset();
		dataset.setId(id);
		dataset.setUserId(param.get("userId"));
		dataset.setName(param.get("name"));
		dataset.setDescription(param.get("description"));
		dataset.setSize(param.get("size"));
		dataset.setType(param.get("type"));
		dataset.setCreateTime(DateTimeUtil.getCurrentTime());
		dataset.setUpdateTime(DateTimeUtil.getCurrentTime());
		
		// 执行数据库保存
		int i = datasetMapper.insert(dataset);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", dataset);
		resultMap.put("code", String.valueOf(i));
		return resultMap;
	}

	@Override
	public String deleteDateset(String id) {
		Example example = new Example(Dataset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = datasetMapper.deleteByExample(example);
		return String.valueOf(i);
	}

	@Override
	public String updateDateset(Map<String,Object> param) {
		Example example = new Example(Dataset.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", (String)param.get("id"));
		
		Dataset dataset = new Dataset();
		String id = UUIDGenerator.getUUID();
		dataset.setId(id);
		dataset.setUserId((String)param.get("userId"));
		dataset.setName((String)param.get("name"));
		dataset.setDescription((String)param.get("description"));
		dataset.setCreateTime((String)DateTimeUtil.getCurrentTime());
		int i = datasetMapper.updateByExampleSelective(dataset, example);
		return String.valueOf(i);
	}

	@Override
	public Map<String, Object> addToProject(Map<String, Object> param) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		List<String> ids = (List<String>)param.get("ids");
		String assetId = (String) param.get("id");
		String userId = (String) param.get("userId");
		int i = 0;
		for (String id  : ids) {
			String paId = UUIDGenerator.getUUID();
			ProjectAsset pa = new ProjectAsset();
			pa.setId(paId);
			pa.setProjectId(id);
			pa.setAssetType("Dataset");
			pa.setAssetId(assetId);
			pa.setUserId(userId);
			pa.setCreateTime(DateTimeUtil.getCurrentTime());
			pa.setUpdateTime(DateTimeUtil.getCurrentTime());
			projectAssetMapper.insert(pa);
		}
		resultMap.put("result", "success");
		return resultMap;
	}
	@Override
	public PagedList<?> getProjectList(Map<String, String> param, int pageNum, int limit) {
		Example example = new Example(ProjectAsset.class);
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("assetId", param.get("id"));
		criteria.andEqualTo("assetType", "Dataset");
		
		Page<ProjectAsset> result = PageHelper.startPage(pageNum, limit)
				.doSelectPage(() -> projectAssetMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
	}

}
