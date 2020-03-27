package dsw.trainjob.service.impl;

import java.util.List;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.trainjob.dao.PresetAlgorithmMapper;
import dsw.trainjob.data.PresetAlgorithm;
import dsw.trainjob.data.TrainJob;
import dsw.trainjob.service.IPresetAlgorithmService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("presetAlgorithmService")
public class PresetAlgorithmServiceImpl implements IPresetAlgorithmService {

	@Autowired
	private PresetAlgorithmMapper presetAlgorithmMapper;

	@Override
	public PagedList<PresetAlgorithm> queryByParam(Map<String, Object> param, int pageNum, int pageSize) {
		Example example = new Example(PresetAlgorithm.class);
		Criteria criteria = example.createCriteria();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			String key = entry.getKey();
			if (key.toUpperCase().endsWith("@LIKE")) {
				key = key.substring(0, key.length() - 5);
				criteria.andLike(key, (String) entry.getValue());
			} else {
				criteria.andEqualTo(key, entry.getValue());
				System.out.println(criteria);
			}
		}
		Page<PresetAlgorithm> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> presetAlgorithmMapper.selectByExample(example));		
		
		return new PagedList<PresetAlgorithm>(result.getResult(), result.getTotal());
	}

	@Override
	public PresetAlgorithm getPresetAlgorithm(int id) {
		return presetAlgorithmMapper.getPresetAlgorithm(id);
	}

	@Override
	public void addPresetAlgorithm(PresetAlgorithm algo) {
		presetAlgorithmMapper.addPresetAlgorithm(algo);
	}

	@Override
	public void updatePresetAlgorithm(Map<String, Object> param) {
		presetAlgorithmMapper.updatePresetAlgorithm(param);
	}

	@Override
	public void removePresetAlgorithm(int id) {
		presetAlgorithmMapper.removePresetAlgorithm(id);
	}
	
}
