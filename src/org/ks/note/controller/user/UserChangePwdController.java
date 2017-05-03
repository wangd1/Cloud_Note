package org.ks.note.controller.user;

import javax.annotation.Resource;

import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserChangePwdController {
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 验证原始密码是否正确
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("valiPwd.do")
	public NoteResult findById(String id,String pwd){
		return userService.findById(id,pwd);
	}
	/**
	 * 修改密码
	 * @param lastpwd
	 * @param newpwd
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePwd.do")
	public NoteResult changePwd(String lastpwd,String newpwd,String id){
		return userService.changePwd(lastpwd, newpwd, id);
	}
}
