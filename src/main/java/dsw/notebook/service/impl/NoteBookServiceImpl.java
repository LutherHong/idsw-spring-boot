package dsw.notebook.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.loushang.framework.base.PagedList;
import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import dsw.notebook.dao.NoteBookMapper;
import dsw.notebook.data.NoteBook;
import dsw.notebook.service.INoteBookService;
import dsw.utils.DateTimeUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("noteBookService")
public class NoteBookServiceImpl implements INoteBookService{

	@Autowired
	private NoteBookMapper noteBookMapper;

	@Override
	public PagedList<NoteBook> getNoteBookList(Map<String, String> param, int pageNum, int pageSize) {
		Example example = new Example(NoteBook.class);
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
		Page<NoteBook> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPage(() -> noteBookMapper.selectByExample(example));		
		
		return new PagedList<>(result.getResult(), result.getTotal());
	}

	@Override
	public Map<String, Object> addNoteBook(Map<String, String> param, int pageNum, int pageSize) {
		String id = UUIDGenerator.getUUID();
		NoteBook exp = new NoteBook();
		exp.setId(id);
		exp.setUserId(param.get("userId"));
		exp.setName(param.get("name"));
		exp.setKernel(param.get("kernel"));
		exp.setCreateTime(DateTimeUtil.getCurrentTime());
		exp.setUpdateTime(DateTimeUtil.getCurrentTime());
		
		// 执行数据库保存
		int i = noteBookMapper.insert(exp);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", exp);
		resultMap.put("code", String.valueOf(i));
		return resultMap;
	}

	@Override
	public String deleteNoteBook(String id) {
		Example example = new Example(NoteBook.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", id);
		int i = noteBookMapper.deleteByExample(example);
		return String.valueOf(i);
	}

	@Override
	public String updateNoteBook(Map<String,Object> param) {
		Example example = new Example(NoteBook.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", (String)param.get("id"));
		
		NoteBook exp = new NoteBook();
		String id = UUIDGenerator.getUUID();
		exp.setId(id);
		exp.setUserId((String)param.get("userId"));
		exp.setName((String)param.get("name"));
		exp.setKernel((String)param.get("kernel"));
		exp.setCreateTime((String)DateTimeUtil.getCurrentTime());
		int i = noteBookMapper.updateByExampleSelective(exp, example);
		return String.valueOf(i);
	}

}
