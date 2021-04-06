package com.aluosu.dao;

import org.apache.ibatis.annotations.*;

import com.aluosu.pojo.Teacher;

public interface TeacherMapper {
	public Teacher getTeacher(@Param("id") int id);
	public Teacher getTeacher2(@Param("id") int id);
}
