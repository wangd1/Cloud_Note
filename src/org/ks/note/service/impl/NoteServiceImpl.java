package org.ks.note.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ks.note.dao.NoteDao;
import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	private NoteDao noteDao;
	public NoteDao getNoteDao() {
		return noteDao;
	}
	@Resource(name="noteDao")
	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	/**
	 * 根据笔记本ID查询笔记
	 */
	@Override
	public NoteResult loadNotes(String bookid) {
		// TODO Auto-generated method stub
		NoteResult result=new NoteResult();
		List<Note> notes=noteDao.findByNoteBookId(bookid);
		if(notes.size()>0){
			result.setStatus(0);
			result.setMsg("查询笔记成功");
			result.setData(notes);
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("查询笔记异常");
			result.setData(null);
			return result;
		}
	}
	/**
	 * 根据笔记id查询笔记信息
	 */
	@Override
	public NoteResult loadBody(String noteid) {
		NoteResult result=new NoteResult();
		Note note=noteDao.findByNoteId(noteid);
		if(note!=null){
			result.setStatus(0);
			result.setMsg("加载笔记内容成功");
			result.setData(note);
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("加载笔记内容异常");
			result.setData(null);
			return result;
		}
	}
	/**
	 * 添加一条笔记
	 */
	@Override
	public NoteResult add(String bookid, String userid, String title) {
		// TODO Auto-generated method stub
		Note note=new Note();
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		String noteid=NoteUtil.createId();
		note.setCn_note_id(noteid);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_title(title);
		note.setCn_note_type_id("5");
		note.setCn_notebook_id(bookid);
		note.setCn_user_id(userid);
		noteDao.save(note);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("添加笔记成功");
		result.setData(noteid);
		return result;
	}
	/**
	 * 更新笔记的标题和内容
	 */
	@Override
	public NoteResult update(String noteid, String title, String body) {
		Map<String, Object> note=new HashMap<String, Object>();
		note.put("cn_note_title", title);
		note.put("cn_note_body", body);
		note.put("cn_note_last_modify_time", System.currentTimeMillis());
		note.put("cn_note_id", noteid);
		noteDao.modify(note);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("保存成功");
		return result;
	}
	/**
	 * 更新笔记状态,即 将笔记放入回收站
	 */
	@Override
	public NoteResult updateStatus(String noteid) {
		NoteResult result=new NoteResult();
		noteDao.updateStatus(noteid);
		result.setStatus(0);
		result.setMsg("笔记已放入回收站");
		result.setData(null);
		return result;
	}
	/**
	 * 移动笔记，更新笔记的cn_notebook_id
	 */
	@Override
	public NoteResult moveNote(String noteid, String bookid) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("cn_note_id", noteid);
		map.put("cn_notebook_id", bookid);
		map.put("cn_note_last_modify_time", System.currentTimeMillis());
		noteDao.move(map);//移动笔记
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("移动笔记成功");
		return result;
	}
	/**
	 * 查询回收站笔记
	 */
	@Override
	public NoteResult loadDisableNotes(String userid) {
		NoteResult result=new NoteResult();
		List<Note> notes=noteDao.findDisableNote(userid);
		result.setStatus(0);
		result.setData(notes);
		return result;
	}
	/**
	 * 彻底删除笔记
	 */
	@Override
	public NoteResult delete(String noteid) {
		NoteResult result=new NoteResult();
		noteDao.delete(noteid);
		result.setStatus(0);
		return result;
	}
	/**
	 * 恢复笔记
	 */
	@Override
	public NoteResult replay(String bookid, String noteid) {
		NoteResult result=new NoteResult();
		Map<String,String> map=new HashMap<String,String>();
		map.put("cn_notebook_id", bookid);//设置参数
		map.put("cn_note_id", noteid);
		noteDao.replayNote(map);//恢复笔记
		result.setStatus(0);
		result.setMsg("恢复笔记成功");
		return result;
	}
	/**
	 * 根据笔记ID获取分享表中的笔记信息
	 */
	@Override
	public Share findByNoteIdFromShare(String noteid) {
		Share share=noteDao.findByNoteIdFromShare(noteid);
		return share;
	}
	/**
	 * 添加收藏笔记到收藏表
	 */
	@Override
	public NoteResult addLike(String noteid,String userid) {
		NoteResult result=new NoteResult();
		Share share=findByNoteIdFromShare(noteid);
		Note note=new Note();
		String id=NoteUtil.createId();
		note.setCn_note_body(share.getCn_share_body());
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(id);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("3");
		note.setCn_note_title(share.getCn_share_title());
		note.setCn_note_type_id("5");
		note.setCn_user_id(userid);
		noteDao.save(note);//插入
		result.setStatus(0);
		result.setMsg("收藏成功");
		return result;
	}
	/**
	 * 根据用户ID查询
	 */
	@Override
	public NoteResult findByUserid(String userid) {
		NoteResult result=new NoteResult();
		List<Note> notes=noteDao.findByUserid();
		System.err.println(notes.size());
		result.setStatus(0);
		result.setData(notes);
		return result;
	}
}
