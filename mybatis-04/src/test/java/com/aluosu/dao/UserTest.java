package com.aluosu.dao;

import java.util.ArrayList;
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
		List<User> userList = new ArrayList<User>();
		userList.add(userDao.selectUser(1));
		
		for(User e : userList) {
			System.out.printf("%s: %s\n", e.getId(),e.getName());
		}
		session.close();
	}
	
	@Test
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("新增一个用户： %s", userMapper.addUser(6, "赵国富", "l006", "12334556797", "6@qq.com" ,"xcvsgsdfs"));
		session.close();
	}
	
	@Test
	public void updateTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("更新一个用户： %s", userMapper.updateUser(6, "赵富", "l006", "0987652341", "6@qq.com" ,"sdfwsfsfw"));
		session.close();
	}
	
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("删除一个用户： %s", userMapper.deleteUser(6));
		session.close();
	}
}
