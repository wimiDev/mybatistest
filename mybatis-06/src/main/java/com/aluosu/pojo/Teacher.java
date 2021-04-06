package com.aluosu.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Teacher {
	private int id;
	private String name;
	List<Student> students;
}
