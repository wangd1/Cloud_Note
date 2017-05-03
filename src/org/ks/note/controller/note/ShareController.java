package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.ShareService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class ShareController {
	private ShareService shareService;
	public ShareService getShareService() {
		return shareService;
	}
	@Resource(name="shareService")
	public void setShareService(ShareService shareService) {
		this.shareService = shareService;
	}
	/**
	 * 验证笔记是否分享过
	 * 如果分享过，就更新分享
	 * 如果没有，就执行下面的插入分享的函数
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateShare.do")
	public NoteResult updateShare(String noteid){
		System.err.println(noteid);
		return shareService.findByNoteid(noteid);
	}
	/**
	 * 分享笔记，插入一条分享
	 * @param noteid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("shareNote.do")
	public NoteResult shareNote(String noteid){
		return shareService.shareNote(noteid);
	}
	/**
	 * 根据笔记标题模糊查询
	 */
	@ResponseBody
	@RequestMapping("searchNote.do")
	public NoteResult searchNote(String title){
		System.err.println(title);
		NoteResult result=shareService.loadNoteByTitle(title);
		return result;
	}
}
