package org.ks.note.test;

import java.util.List;

import org.junit.Test;
import org.ks.note.dao.NoteDao;
import org.ks.note.entity.Note;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNote {
	@Test
	public void test(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		List<Note> lists=ac.getBean("noteDao",NoteDao.class).findByNoteBookId("4b86d1f9-6345-4532-bc50-ee86442f004b");
		System.out.println(lists.size());
	}
}
