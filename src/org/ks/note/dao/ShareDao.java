package org.ks.note.dao;

import java.util.List;
import java.util.Map;

import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.util.IMyBatisUtil;
import org.ks.note.util.PageUtil;

@IMyBatisUtil
public interface ShareDao {
	public List<Share> findByNoteid(String noteid);
	public int insert(Share share);
	//根据笔记id获取笔记信息
	public Note findNoteById(String noteid);
	public int update(Map<String, Object> map);
	//模糊查询
	public List<Share> findByTitle(Map<String, String> map);
	public List<Share> findUsePage(PageUtil page);
}
