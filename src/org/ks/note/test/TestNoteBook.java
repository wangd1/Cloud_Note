package org.ks.note.test;

import java.util.List;

import org.junit.Test;
import org.ks.note.dao.NoteBookDao;
import org.ks.note.entity.NoteBook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNoteBook {
	@Test
	public void test(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		NoteBookDao dao=ac.getBean("noteBookDao",NoteBookDao.class);
		List<NoteBook> notebook = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(notebook.size());
	}
}
