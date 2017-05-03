package org.ks.note.dao;

import java.util.Map;

import org.ks.note.entity.User;
import org.ks.note.util.IMyBatisUtil;

@IMyBatisUtil
public interface UserDao {
	public User findByName(String name);
	public User findById(String userid);
	public int insert(User user);
	public int changePassword(Map<String, Object> user);
}
