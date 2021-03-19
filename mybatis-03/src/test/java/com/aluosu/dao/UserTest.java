package com.aluosu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.aluosu.pojo.User;
import com.aluosu.utils.MyBatisUtil;
import com.github.pagehelper.PageHelper;

public class UserTest {
	@Test
	public void test() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		List<User> userList = userDao.getUserList();
		
		for(User e : userList) {
			System.out.printf("%s, %s\n",e.getId(), e.getN());
		}
		session.close();
	}
	
	@Test
	public void getUserByLimit(){
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		PageHelper.startPage(3, 3);
		List<User> userList = userMapper.getUserList();
		for(User e : userList) {
			System.out.printf("%s, %s\n",e.getId(), e.getN());
		}
		session.close();
	}
	
	@Test
	public void getUserTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		User user = userDao.getUserById(1);
		System.out.printf("%s, %s\n",user.getId(), user.getN());
		session.close();
	}
	
	@Test
	public void userLikeTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		List<User> userList = userDao.getUserLike("鏉�");
		
		for(User e : userList) {
			System.out.printf("%s\n",e);
		}
		session.close();
	}
	
	@Test
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(5, "鏉庨","1005","12345678910", "5@luosu.com", "66666666666");
		int result = userMap.addUser(nUser);
		System.out.printf("鎻掑叆涓�涓敤鎴� result: %d\n", result);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void insertTestByMap() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		Map<String, Object> nUser = new HashMap<String, Object>();
		nUser.put("id", 2);
		nUser.put("name", "鍙戣储");
		nUser.put("id", 2);
		nUser.put("code", "1002");
		nUser.put("phone", "12345678910");
		nUser.put("email", "1@luosu.com");
		nUser.put("addr", "333333333333");
		
		int result = userMap.addUserByMap(nUser);
		System.out.printf("鎻掑叆涓�涓敤鎴� result: %d\n", result);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		int result = userMap.delUser(1);
		System.out.printf("鍒犻櫎涓�涓敤鎴� result: %d\n", result);
		session.commit();
		session.close();
	}
	
	@Test
	public void updateTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(4, "goodjob","1004","0987654321", "what email?", "no house");
		int result = userMap.updateUser(nUser);
		System.out.printf("鏇存柊涓�涓敤鎴� result: %d\n", result);
		session.commit();
		session.close();
	}
}
