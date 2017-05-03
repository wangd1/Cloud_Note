package org.ks.note.controller.notebook;

import javax.annotation.Resource;

import org.ks.note.service.NoteBookService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class AddNoteBookController {
	private NoteBookService noteBookService;
	public NoteBookService getNoteBookServcie() {
		return noteBookService;
	}
	@Resource(name="noteBookService")
	public void setNoteBookServcie(NoteBookService noteBookService) {
		this.noteBookService = noteBookService;
	}
	/**
	 * 添加一个笔记本
	 * @param bookname
	 * @param bookid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add.do")
	public NoteResult add(String bookname,String userid){
		return noteBookService.add(bookname, userid);
	}
	
}
