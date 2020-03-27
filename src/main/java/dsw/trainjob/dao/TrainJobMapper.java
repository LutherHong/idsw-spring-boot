package dsw.trainjob.dao;

import java.util.List;
import java.util.Map;

import dsw.trainjob.data.TrainJob;
import tk.mybatis.mapper.common.Mapper;

public interface TrainJobMapper extends Mapper<TrainJob>{

	List<TrainJob> queryByParam(Map<String, Object> param);
	
	TrainJob getTrainJob(String id);

	void addTrainJob(TrainJob trainJob);

	void updateTrainJob(TrainJob trainJob);

	void removeTrainJob(String id);

}
