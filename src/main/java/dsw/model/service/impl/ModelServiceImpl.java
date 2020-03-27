package dsw.model.service.impl;

import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.model.dao.ModelMapper;
import dsw.model.data.Model;
import dsw.model.service.IModelService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("modelService")
public class ModelServiceImpl implements IModelService {

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PagedList<Model> getModelList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(Model.class);
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
		Page<Model> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> modelMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
	}
	
	@Override
	public String deleteModel(String id) {
		Example example = new Example(Model.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = modelMapper.deleteByExample(example);
		return String.valueOf(i);
	}

}
