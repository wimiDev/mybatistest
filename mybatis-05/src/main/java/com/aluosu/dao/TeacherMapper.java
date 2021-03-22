package com.aluosu.dao;

import org.apache.ibatis.annotations.*;

import com.aluosu.pojo.Teacher;

public interface TeacherMapper {
	
	@Select("select * from ${DATABASE}.${TEACHER} where id=#{id}")
	public Teacher getTeacher(@Param("id") int id);
}
