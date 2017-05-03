package org.ks.note.test;

import org.junit.Assert;
import org.junit.Test;
import org.ks.note.dao.UserDao;
import org.ks.note.entity.User;
import org.ks.note.util.NoteUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
	@Test
	public void TestLogin(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao dao=ac.getBean("userDao",UserDao.class);
		User user=dao.findByName("张三");
		//断言
		Assert.assertEquals("小三1", user.getCn_user_desc());
	}
	@Test
	public void testInsert(){
		ApplicationContext ac=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao dao=ac.getBean("userDao",UserDao.class);
		User user=new User();
		user.setCn_user_id(NoteUtil.createId());//创建ID
		user.setCn_user_name("张三");
		user.setCn_user_password(NoteUtil.md5("123456"));//加密密码
		user.setCn_user_desc("小三");
		int i=dao.insert(user);//注册用户
		Assert.assertEquals(1, i);
	}
}
