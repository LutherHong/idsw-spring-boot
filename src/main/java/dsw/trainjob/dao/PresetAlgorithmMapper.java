package dsw.trainjob.dao;

import java.util.List;
import java.util.Map;

import dsw.trainjob.data.PresetAlgorithm;
import tk.mybatis.mapper.common.Mapper;

public interface PresetAlgorithmMapper extends Mapper<PresetAlgorithm>{
	public List<PresetAlgorithm> queryByParam(Map<String,Object> param);
	
	public PresetAlgorithm getPresetAlgorithm(int id);
	
	public void addPresetAlgorithm(PresetAlgorithm algo);
	
	public void updatePresetAlgorithm(Map<String,Object> param);
	
	public void removePresetAlgorithm(int id);
}
