package com.aluosu.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static MyBatisUtil _inatance;
	
	static {
		_inatance = new MyBatisUtil();
	}
	
	private MyBatisUtil() {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private SqlSessionFactory sqlSessionFactory;
	
	//返回单例
	static public MyBatisUtil getInstance() {
		return _inatance;
	}
	
	//	返回sql会话
	public SqlSession getSession() {
		return sqlSessionFactory.openSession(true);
	}
}
