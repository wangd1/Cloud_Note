package org.ks.note.dao;

import java.util.List;
import java.util.Map;

import org.ks.note.entity.NoteBook;
import org.ks.note.util.IMyBatisUtil;

@IMyBatisUtil
public interface NoteBookDao {
	public List<NoteBook> findByUserId(String userId);
	public int save(NoteBook notebook);
	public int updateNoteBookName(Map<String, Object> notebook);
	public int delete(String notebookid);
}
