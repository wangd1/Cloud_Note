package org.ks.note.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.ks.note.dao.UserDao;
import org.ks.note.entity.User;
import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 检测用户登陆
	 */
	@Override
	public NoteResult checkLogin(String cn_user, String cn_password) {
		NoteResult result=new NoteResult();
		User user=userDao.findByName(cn_user);
		//检测用户
		if(user==null){
			//状态：0：成功,1:用户名不正确,2:密码不正确
			result.setStatus(1);
			result.setMsg("用户名不存在！");
			return result;
		}
		//对输入的密码加密
		if(!user.getCn_user_password().equals(NoteUtil.md5(cn_password))){
			//密码不正确
			result.setStatus(2);
			result.setMsg("密码不正确！");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登陆成功！");
		result.setData(user.getCn_user_id());
		return result;
	}
	//注册
	@Override
	public NoteResult registe(String username,String password,String nickname) {
		// TODO Auto-generated method stub
		NoteResult result=new NoteResult();
		//检测用户名是否注册
		User dbuser=userDao.findByName(username);
		if(dbuser!=null){
			result.setStatus(1);
			result.setMsg("用户名已注册!!");
			return result;
		}
		//执行注册
		User user=new User();
		user.setCn_user_id(NoteUtil.createId());
		user.setCn_user_name(username);
		user.setCn_user_password(NoteUtil.md5(password));
		user.setCn_user_token(null);
		user.setCn_user_desc(nickname);
		userDao.insert(user);
		result.setStatus(0);
		result.setMsg("注册成功!!");
		return result;
	}
	/**
	 * 修改密码
	 */
	@Override
	public NoteResult changePwd(String lastpwd, String newpwd,String id) {
		NoteResult result=new NoteResult();
		Map<String, Object> ma=new HashMap<String,Object>();
		ma.put("cn_user_password", NoteUtil.md5(newpwd));
		ma.put("cn_user_id", id);
		userDao.changePassword(ma);
		result.setStatus(0);
		result.setMsg("修改密码成功");
		return result;
	}
	/**
	 * 根据ID查找用户
	 */
	@Override
	public NoteResult findById(String id,String pwd) {
		NoteResult result=new NoteResult();
		User user=userDao.findById(id);
		pwd=NoteUtil.md5(pwd);
		System.err.println(user.getCn_user_password());
		System.err.println(pwd);
		if(!pwd.equals(user.getCn_user_password())){
			result.setStatus(1);
			result.setMsg("*原始密码不正确");
			return result;
		}
		result.setStatus(0);
		return result;
	}
	
	
}
