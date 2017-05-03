package org.ks.note.service.test;

import org.junit.Test;
import org.ks.note.service.impl.UserServiceImpl;
import org.ks.note.util.NoteResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {
	@Test
	public void testLogin(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl user=ac.getBean("userService",UserServiceImpl.class);
		NoteResult result=user.checkLogin("yht", "96e79218965eb72c92a549dd5a330112");
		System.out.println(result.getStatus()+" "+result.getMsg());
	}
	@Test
	public void testRegite(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl user=ac.getBean("userService",UserServiceImpl.class);
		NoteResult result=user.registe("李四", "123123", "小四");
		System.out.println(result.getMsg());
		
	}
}
