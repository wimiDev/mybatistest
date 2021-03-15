package com.aluosu.dao;
import java.util.List;
import java.util.Map;

import com.aluosu.pojo.*;

public interface UserMapper {
	public List<User> getUserList();
	
	public List<User> getUserLike(String value);
	
	public int addUser(User user);
	
	public int delUser(int id);
	
	public int updateUser(User user);
	
	public int addUserByMap(Map user);
}
