package dsw.trainjob.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;

import dsw.trainjob.data.PresetAlgorithm;

public interface IPresetAlgorithmService {
	
	public PagedList<PresetAlgorithm> queryByParam(Map<String,Object> param, int pageNum, int pageSize);
	
	public PresetAlgorithm getPresetAlgorithm(int id);
	
	public void addPresetAlgorithm(PresetAlgorithm algo);
	
	public void updatePresetAlgorithm(Map<String,Object> param);
	
	public void removePresetAlgorithm(int id);
}
