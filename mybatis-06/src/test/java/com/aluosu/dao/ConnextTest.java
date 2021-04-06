package com.aluosu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.aluosu.pojo.Student;
import com.aluosu.pojo.Teacher;
import com.aluosu.utils.MyBatisUtil;

public class ConnextTest {
	
	@Test
	public void getTeacher() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		TeacherMapper teacherDao = session.getMapper(TeacherMapper.class);
		List<Teacher> teacherList = new ArrayList<Teacher>();
//		teacherList.add(teacherDao.getTeacher(1));
		teacherList.add(teacherDao.getTeacher2(1));
		
		for(Teacher e : teacherList) {
			System.out.print(e);
		}
		session.close();
	}

}
