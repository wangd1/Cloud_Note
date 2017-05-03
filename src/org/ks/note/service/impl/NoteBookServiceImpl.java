package org.ks.note.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ks.note.dao.NoteBookDao;
import org.ks.note.entity.NoteBook;
import org.ks.note.service.NoteBookService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service(value="noteBookService")
public class NoteBookServiceImpl implements NoteBookService {
	private NoteBookDao noteBookDao;
	public NoteBookDao getNoteBookDao() {
		return noteBookDao;
	}
	//注入Dao
	@Resource(name="noteBookDao")
	public void setNoteBookDao(NoteBookDao noteBookDao) {
		this.noteBookDao = noteBookDao;
	}
	/**
	 * 根据用户ID查找所有笔记本
	 */
	@Override
	public NoteResult loadNoteBook(String userId) {
		// TODO Auto-generated method stub
		List<NoteBook> notebooks=noteBookDao.findByUserId(userId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(notebooks);
		return result;
	}
	/**
	 * 添加一个笔记本
	 */
	@Override
	public NoteResult add(String bookname, String userid) {
		// TODO (可以追加笔记本重名验证)
		NoteResult result=new NoteResult();
		NoteBook book=new NoteBook();
		book.setCn_notebook_name(bookname);
		book.setCn_user_id(userid);
		String bookid=NoteUtil.createId();
		book.setCn_notebook_id(bookid);
		book.setCn_notebook_desc("");
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_create_time(new Timestamp(System.currentTimeMillis()));
		noteBookDao.save(book);
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(bookid);//将新建的笔记本的ID返回
		return result;
	}
	/**
	 * 笔记本的重命名
	 */
	@Override
	public NoteResult updateNoteBookName(String notebookName, String notebookId) {
		NoteResult result=new NoteResult();
		Map<String, Object> notebook=new HashMap<String,Object>();
		//设置参数
		notebook.put("cn_notebook_id", notebookId);
		notebook.put("cn_notebook_name",notebookName);
		//重命名
		noteBookDao.updateNoteBookName(notebook);
		result.setStatus(0);
		result.setMsg("笔记本重命名成功");
		return result;
	}
	/**
	 * 删除笔记本,笔记本为空的情况下删除
	 */
	@Override
	public NoteResult delNoteBook(String notebookid) {
		NoteResult result=new NoteResult();
		noteBookDao.delete(notebookid);
		result.setStatus(0);
		result.setMsg("删除笔记本成功");
		return result;
	}
	
}
