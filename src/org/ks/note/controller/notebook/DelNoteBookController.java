package org.ks.note.controller.notebook;

import javax.annotation.Resource;

import org.ks.note.service.NoteBookService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class DelNoteBookController {
	private NoteBookService noteBookService;
	public NoteBookService getNoteBookServcie() {
		return noteBookService;
	}
	@Resource(name="noteBookService")
	public void setNoteBookServcie(NoteBookService noteBookService) {
		this.noteBookService = noteBookService;
	}
	//É¾³ý±Ê¼Ç±¾
	@ResponseBody
	@RequestMapping("delNoteBook.do")
	public NoteResult delNoteBook(String bookid){
		return noteBookService.delNoteBook(bookid);
	}
}
