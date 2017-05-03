package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	private NoteService noteService;
	public NoteService getNoteService() {
		return noteService;
	}
	@Resource(name="noteService")
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
	/**
	 * 保存笔记
	 * @param noteid
	 * @param title
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update.do")
	public NoteResult update(String noteid,String title,String body){
		return noteService.update(noteid, title, body);
	}
	/**
	 * 将笔记放入回收站
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateStatus.do")
	public NoteResult updateStatus(String noteid){
		return noteService.updateStatus(noteid);
	}
}
