package org.ks.note.controller.notebook;

import javax.annotation.Resource;

import org.ks.note.service.NoteBookService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class UpdateNoteBookController {
	private NoteBookService noteBookService;
	public NoteBookService getNoteBookServcie() {
		return noteBookService;
	}
	@Resource(name="noteBookService")
	public void setNoteBookServcie(NoteBookService noteBookService) {
		this.noteBookService = noteBookService;
	}
	/**
	 * 笔记本的重命名
	 * @param notebookName
	 * @param notebookId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rename.do")
	public NoteResult updateNoteBookName(String notebookName,String notebookId){
		System.err.println(notebookName+" "+notebookId);
		return noteBookService.updateNoteBookName(notebookName, notebookId);
	}
}
