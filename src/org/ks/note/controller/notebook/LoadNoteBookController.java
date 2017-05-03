package org.ks.note.controller.notebook;

import javax.annotation.Resource;

import org.ks.note.service.NoteBookService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class LoadNoteBookController {
	private NoteBookService noteBookService;
	public NoteBookService getNoteBookServcie() {
		return noteBookService;
	}
	@Resource(name="noteBookService")
	public void setNoteBookServcie(NoteBookService noteBookService) {
		this.noteBookService = noteBookService;
	}
	/**
	 * 根据用户id查询用户的所有笔记本
	 * @param cn_user_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loadNoteBook.do")
	public NoteResult loadNoteBook(String cn_user_id){
		NoteResult result=noteBookService.loadNoteBook(cn_user_id);
		return result;
	}
}
