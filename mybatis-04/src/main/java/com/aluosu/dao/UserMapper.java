package com.aluosu.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.aluosu.pojo.*;

public interface UserMapper {
	@Select("SELECT * FROM ${DATABASE}.${USER} where id = #{id}")
	public User selectUser(@Param("id") int id);
	
	@Insert("INSERT into ${DATABASE}.${USER}(id, name, code, phone, email, addr) values(#{id}, #{name}, #{code}, #{phone}, #{email}, #{addr})")
	public int addUser(@Param("id") int id, @Param("name")String name, @Param("code")String code, @Param("phone")String phone, @Param("email")String email, @Param("addr")String addr);
	
	@Update("update ${DATABASE}.${USER} set \r\n"
			+ "  	name = #{name},\r\n"
			+ "  	code = #{code},\r\n"
			+ "  	phone = #{phone},\r\n"
			+ "  	email = #{email},\r\n"
			+ "  	addr = #{addr}\r\n"
			+ "  	where id = #{id}")
	public int updateUser(@Param("id") int id, @Param("name")String name, @Param("code")String code, @Param("phone")String phone, @Param("email")String email, @Param("addr")String addr);
	
	@Delete("delete from ${DATABASE}.${USER} where id = #{id}")
	public int deleteUser(@Param("id") int id);
}
