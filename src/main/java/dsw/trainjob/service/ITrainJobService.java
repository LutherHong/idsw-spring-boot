package dsw.trainjob.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;

import dsw.trainjob.data.TrainJob;

public interface ITrainJobService {
	
	PagedList<?> getList(Map<String, Object> param, int pageNum, int pageSize);
	
	public TrainJob getTrainJob(String id);
	
	public void addTrainJob(Map<String,Object> param);
	
	public String updateTrainJob(Map<String,Object> param);
	
	public String deleteTrainJob(String id);

}
