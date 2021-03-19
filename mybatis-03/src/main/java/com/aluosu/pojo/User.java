package com.aluosu.pojo;

public class User {
	private int id;
	private String n;
	private String code;
	private String phone;
	private String email;
	private String addr;
	
	public User( int id,
	 String name,
	 String code,
	 String phone,
	 String email,
	 String addr){
		this.id = id;
		this.n = name;
		this.code = code;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
	}
	
	public void setId(int _id) {
		id = _id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setN(String _name) {
		n = _name;
	}
	public String getN() {
		return n;
	}
	
	public void setCode(String _code) {
		code = _code;
	}
	public String getCode() {
		return code;
	}
	
	public void setPhone(String _phone) {
		phone = _phone;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setEmail(String _emial) {
		email = _emial;
	}
	public String Email() {
		return  email;
	}
	
	public void setAddr(String _addr) {
		addr = _addr;
	}
	public String getAddr() {
		return addr;
	}
};
