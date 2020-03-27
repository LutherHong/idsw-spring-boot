package dsw.project.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dsw.project.dao.ProjectMapper;
import dsw.project.data.Project;
import dsw.project.data.ProjectView;
import dsw.project.service.IProjectAssetService;
import dsw.project.service.IProjectService;
import dsw.utils.DateTimeUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private IProjectAssetService projectAssetService;
	@Autowired
	public List<Project> listProjects(Map<String, Object> param) {
		Example example = new Example(Project.class);
		Criteria criteria = example.createCriteria();
		//条件！！！
		String ownerId=(String) param.get("ownerId");
		criteria.andEqualTo("ownerId", ownerId);
		List<Project> list=projectMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public PagedList<?> getProjectList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(Project.class);
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
		Page<?> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> projectMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());

	}

	@Override
	public Map<String, Object> addProject(Map<String, String> param, int pageNum, int pageSize) {
		String id = UUIDGenerator.getUUID();
		Project project = new Project();
		project.setId(id);
		project.setOwnerId(param.get("ownerId"));
		project.setName(param.get("name"));
		project.setDescription(param.get("description"));
		project.setCreatedAt(DateTimeUtil.getCurrentTime());
		
		// 执行数据库保存
		int i = projectMapper.insert(project);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", project);
		resultMap.put("code", String.valueOf(i));
		return resultMap;
	}

	@Override
	public String deleteProject(String id) {
		Example example = new Example(Project.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = projectMapper.deleteByExample(example);
		return String.valueOf(i);
	}

	@Override
	public String updateProject(Map<String,Object> param) {
		Example example = new Example(Project.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", (String)param.get("id"));
		
		Project project = new Project();
		project.setId((String)param.get("id"));
		project.setOwnerId((String)param.get("ownerId"));
		project.setName((String)param.get("name"));
		project.setDescription((String)param.get("description"));
		project.setUpdatedAt(DateTimeUtil.getCurrentTime());
		int i = projectMapper.updateByExampleSelective(project, example);
		return String.valueOf(i);
	}

	@Override
	public ProjectView getProjectView(String projectId) {
		// TODO Auto-generated method stub
		ProjectView pv=new ProjectView();
		Project project=projectMapper.selectByPrimaryKey(projectId);
		pv.setId(projectId);
		pv.setName(project.getName());
		pv.setDescription(project.getDescription());
		pv.setCreateTime(project.getCreatedAt());
		pv.setUpdateTime(project.getUpdatedAt());
		pv.setUserId(project.getOwnerId());
		
		pv.setDataSetCount(projectAssetService.getCountOfDataSet(projectId));
		pv.setExperimentCount(projectAssetService.getCountOfExperiment(projectId));
		pv.setModelCount(projectAssetService.getCountOfModel(projectId));
		pv.setNotebookCount(projectAssetService.getCountOfNotebook(projectId));
		pv.setDeployCount(projectAssetService.getCountOfDeploy(projectId));
		
		pv.setDataSetList(projectAssetService.getDataSetList(projectId));
		pv.setExperimentList(projectAssetService.getExperimentList(projectId));
		pv.setModelList(projectAssetService.getModelList(projectId));
		pv.setNotebookList(projectAssetService.getNotebookList(projectId));
		pv.setDeployList(projectAssetService.getDeployList(projectId));
		return pv;
	}

	@Override
	public PagedList<?> getProjectViewList(Map<String, Object> params) {
		// TODO Auto-generated method stub
/*		int pageNum=(int) params.get("pageNum");
		int pageSize=(int) params.get("pageSize");*/
		List<Project> projectList=listProjects(params);
		List<ProjectView> pvList=new ArrayList<ProjectView>();
		if(projectList.isEmpty()){
			return null;
		}
		for(Project project: projectList){
			ProjectView pv=new ProjectView();
			String projectId=project.getId();
			pv.setId(projectId);
			pv.setName(project.getName());
			pv.setDescription(project.getDescription());
			pv.setCreateTime(project.getCreatedAt());
			pv.setUpdateTime(project.getUpdatedAt());
			pv.setUserId(project.getOwnerId());
			
			pv.setDataSetCount(projectAssetService.getCountOfDataSet(projectId));
			pv.setExperimentCount(projectAssetService.getCountOfExperiment(projectId));
			pv.setModelCount(projectAssetService.getCountOfModel(projectId));
			pv.setNotebookCount(projectAssetService.getCountOfNotebook(projectId));
			pv.setDeployCount(projectAssetService.getCountOfDeploy(projectId));
			
			pvList.add(pv);
		}
			
		return new PagedList<>(pvList, pvList.size());
	}

}
