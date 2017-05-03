package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class MoveController {
	private NoteService noteService;
	public NoteService getNoteService() {
		return noteService;
	}
	@Resource(name="noteService")
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
	/**
	 * 移动笔记，更新笔记的cn_notebook_id
	 * @param noteid
	 * @param bookid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/moveNote.do")
	public NoteResult moveNote(String noteid,String bookid){
		return noteService.moveNote(noteid, bookid);
	}
}
