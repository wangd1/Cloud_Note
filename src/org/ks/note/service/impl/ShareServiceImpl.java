package org.ks.note.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ks.note.dao.ShareDao;
import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.service.ShareService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.ks.note.util.PageUtil;
import org.springframework.stereotype.Service;

@Service("shareService")
public class ShareServiceImpl implements ShareService{

	private ShareDao shareDao;
	public ShareDao getShareDao() {
		return shareDao;
	}
	@Resource(name="shareDao")
	public void setShareDao(ShareDao shareDao) {
		this.shareDao = shareDao;
	}

	/**
	 * 根据笔记id查询分享表，判断笔记是否分享过
	 */
	@Override
	public NoteResult findByNoteid(String noteid) {
		NoteResult result=new NoteResult();
		List<Share> share=shareDao.findByNoteid(noteid);
		if(share.size()<=0){
			//未分享过
			result.setStatus(0);
			return result;
		}
		//分享过，执行更新操作
		result.setStatus(1);
		result.setMsg("分享成功");
		update(noteid);
		return result;
	}
	//分享笔记
	@Override
	public NoteResult shareNote(String noteid) {
		NoteResult result=new NoteResult();
		Note note=findNoteById(noteid);//获取笔记信息
		Share share=new Share();
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_note_id(note.getCn_note_id());
		int i=shareDao.insert(share);//插入一条分享笔记
		result.setStatus(0);
		result.setMsg("分享成功");
		return result;
	}
	//根据笔记id获取笔记信息
	@Override
	public Note findNoteById(String id) {
		Note note=shareDao.findNoteById(id);
		return note;
	}
	//再次分享时执行更新操作
	@Override
	public void update(String noteid) {
		Note note=findNoteById(noteid);
		Map<String, Object> share=new HashMap<String,Object>();
		share.put("cn_share_title", note.getCn_note_title());
		share.put("cn_share_body", note.getCn_note_body());
		share.put("cn_note_id", noteid);
		shareDao.update(share);
	}
	/**
	 * 根据笔记标题模糊查询
	 */
	@Override
	public NoteResult loadNoteByTitle(String cn_share_title) {
		NoteResult result=new NoteResult();
		Map<String, String> map=new HashMap<String,String>();
		map.put("cn_share_title", cn_share_title);
		List<Share> list=shareDao.findByTitle(map);
		result.setStatus(0);
		result.setData(list);
		return result;
	}
	/**
	 * 分页查询
	 */
	@Override
	public NoteResult findUsePage(PageUtil page) {
		List<Share> data=shareDao.findUsePage(page);
		NoteResult result=new NoteResult();
		result.setData(data);
		return result;
	}
}
