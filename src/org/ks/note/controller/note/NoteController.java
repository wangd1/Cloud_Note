package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class NoteController {
	private NoteService noteService;
	public NoteService getNoteService() {
		return noteService;
	}
	@Resource(name="noteService")
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
	/**
	 * 根据笔记本ID查询笔记列表,返回json格式数据
	 * @param bookid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadNotes.do")
	public NoteResult loadNotes(String bookid){
		System.err.println(bookid);
		return noteService.loadNotes(bookid);
	}
	/**
	 * 根据笔记ID查询笔记
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadBody.do")
	public NoteResult loadBody(String noteid){
//		System.err.println(noteid);
		return noteService.loadBody(noteid);
	}
	/**
	 * 加载回收站笔记
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadDisableNotes.do")
	public NoteResult loadDisableNote(String userid){
		return noteService.loadDisableNotes(userid);
	}
	/**
	 * 彻底删除笔记
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteRollbackNote.do")
	public NoteResult deleteRollbackNote(String noteid){
		return noteService.delete(noteid);
	}
	/**
	 * 恢复笔记
	 * @param bookid
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sureReplay.do")
	public NoteResult sureReplay(String bookid,String noteid){
		return noteService.replay(bookid, noteid);
	}
}
