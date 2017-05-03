package org.ks.note.service.test;

import java.util.List;

import org.junit.Test;
import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.service.NoteBookService;
import org.ks.note.service.NoteService;
import org.ks.note.service.ShareService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.PageUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNoteBook {
	@Test
	public void testLogin(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		NoteBookService book=ac.getBean("noteBookService",NoteBookService.class);
		System.out.println(book.loadNoteBook("48595f52-b22c-4485-9244-f4004255b972").getData());
	}
	
	@Test
	public void test(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		ShareService note=ac.getBean("shareService",ShareService.class);
		NoteResult result=note.loadNoteByTitle("–ƒ");
		List<Share> notes=(List<Share>) result.getData();
		System.out.println(notes.get(0).getCn_share_title());
	}
	//∑÷“≥≤È—Ø≤‚ ‘
	@Test
	public void test1(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		ShareService note=ac.getBean("shareService",ShareService.class);
		PageUtil page=new PageUtil();
		page.setCurrentPage(1);
		page.setPageSize(5);
		NoteResult result=note.findUsePage(page);
		List<Share> notes=(List<Share>) result.getData();
		for (Share share : notes) {
			System.out.println(share.getCn_share_title());
		}
	}
}
