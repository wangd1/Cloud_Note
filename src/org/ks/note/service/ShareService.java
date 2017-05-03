package org.ks.note.service;

import org.ks.note.entity.Note;
import org.ks.note.util.NoteResult;
import org.ks.note.util.PageUtil;

public interface ShareService {
	//根据笔记id获取分享笔记信息
	public NoteResult findByNoteid(String noteid);
	//分享笔记，插入一条分享笔记
	public NoteResult shareNote(String noteid);
	//根据笔记id获取笔记信息
	public Note findNoteById(String id);
	//笔记已经分享过时，执行更新笔记操作
	public void update(String noteid);
	public NoteResult loadNoteByTitle(String cn_share_title);
	public NoteResult findUsePage(PageUtil page);
}
