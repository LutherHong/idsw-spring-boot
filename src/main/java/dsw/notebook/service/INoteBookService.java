package dsw.notebook.service;

import java.util.Map;

import org.loushang.framework.base.PagedList;

import dsw.notebook.data.NoteBook;

public interface INoteBookService {
	PagedList<NoteBook> getNoteBookList(Map<String, String> param, int pageNum, int pageSize);

	Map<String, Object> addNoteBook(Map<String, String> param, int pageNum, int pageSize);

	String deleteNoteBook(String id);

	String updateNoteBook(Map<String, Object> param);
}
