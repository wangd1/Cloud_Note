package org.ks.note.dao;

import java.util.List;
import java.util.Map;

import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.util.IMyBatisUtil;

@IMyBatisUtil
public interface NoteDao {
	//根据笔记本ID查询当前笔记本中的笔记
	public List<Note> findByNoteBookId(String bookid);
	//根据笔记ID查询笔记
	public Note findByNoteId(String noteid);
	//插入笔记
	public int save(Note note);
	//保存笔记
	public int modify(Map<String, Object> map);
	//将笔记放入回收站
	public int updateStatus(String noteid);
	//移动笔记，更新笔记的cn_notebook_id
	public int move(Map<String, Object> map);
	//查询回收站笔记
	public List<Note> findDisableNote(String userid);
	//彻底删除笔记
	public int delete(String noteid);
	//恢复笔记
	public int replayNote(Map<String,String> map);
	//根据笔记ID获取分享表中的笔记信息
	public Share findByNoteIdFromShare(String noteid);
	public List<Note> findByUserid();
}
