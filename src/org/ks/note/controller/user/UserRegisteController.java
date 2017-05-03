package org.ks.note.controller.user;

import javax.annotation.Resource;

import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserRegisteController {
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//зЂВс
	@RequestMapping("registe.do")
	@ResponseBody
	public NoteResult registe(String regist_username,String nickname,String regist_password){
		return userService.registe(regist_username, regist_password, nickname);
	}
}
