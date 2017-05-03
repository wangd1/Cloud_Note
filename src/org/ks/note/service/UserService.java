package org.ks.note.service;

import org.ks.note.util.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String cn_user,String cn_password);
	public NoteResult registe(String username,String password,String nickname);
	public NoteResult changePwd(String lastpwd,String newpwd,String id);
	public NoteResult findById(String id,String pwd);
}
