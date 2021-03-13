package com.aluosu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.aluosu.pojo.User;
import com.aluosu.utils.MyBatisUtil;

public class UserTest {
	@Test
	public void test() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		List<User> userList = userDao.getUserList();
		
		for(User e : userList) {
			System.out.printf("%s\n",e);
		}
		session.close();
	}
	
	@Test
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(1, "暴富脱单","1001","12345678910", "1@luosu.com", "2222222");
		int result = userMap.addUser(nUser);
		System.out.printf("插入一个用户 result: %d\n", result);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void insertTestByMap() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		Map<String, Object> nUser = new HashMap<String, Object>();
		nUser.put("id", 2);
		nUser.put("name", "发财");
		nUser.put("id", 2);
		nUser.put("code", "1002");
		nUser.put("phone", "12345678910");
		nUser.put("email", "1@luosu.com");
		nUser.put("addr", "333333333333");
		
		int result = userMap.addUserByMap(nUser);
		System.out.printf("插入一个用户 result: %d\n", result);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		int result = userMap.delUser(1);
		System.out.printf("删除一个用户 result: %d\n", result);
		session.commit();
		session.close();
	}
	
	@Test
	public void updateTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(4, "goodjob","1004","0987654321", "what email?", "no house");
		int result = userMap.updateUser(nUser);
		System.out.printf("更新一个用户 result: %d\n", result);
		session.commit();
		session.close();
	}
}
