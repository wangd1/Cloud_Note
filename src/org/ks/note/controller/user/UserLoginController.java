package org.ks.note.controller.user;


import javax.annotation.Resource;

import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//ÓÃ»§µÇÂ½
	@ResponseBody
	@RequestMapping("login.do")
	public NoteResult login(String username,String password){
		NoteResult result=userService.checkLogin(username, password);
//		System.out.println(username+" "+password);
//		System.out.println(NoteUtil.md5(password));
		return result;
	}
}
