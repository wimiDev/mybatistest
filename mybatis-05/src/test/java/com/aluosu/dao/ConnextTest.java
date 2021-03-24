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
		teacherList.add(teacherDao.getTeacher(1));
		
		for(Teacher e : teacherList) {
			System.out.printf("%s: %s\n", e.getId(),e.getName());
		}
		session.close();
	}
	
	@Test
	public void getStudentTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		List<Student> studentList = studentMapper.getStudent();
//		studentList.add(studentDao.getStudent());
		
		for(Student e : studentList) {
			System.out.printf("%s: %s\n", e.getId(),e.getName());
		}
		session.close();
	}

}
