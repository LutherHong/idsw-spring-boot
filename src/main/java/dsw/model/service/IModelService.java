package dsw.model.service;

import java.util.Map;
import org.loushang.framework.base.PagedList;
import dsw.model.data.Model;

public interface IModelService {
	PagedList<Model> getModelList(Map<String, String> param, int pageNum, int pageSize);

	String deleteModel(String id);
}
