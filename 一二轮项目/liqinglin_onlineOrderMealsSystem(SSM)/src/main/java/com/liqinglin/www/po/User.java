package com.liqinglin.www.po;

import java.sql.Timestamp;

public class User {
	private int id;            //用户id
	private String username;    //用户名
	private String password;    //密码
	private String phone;       //联系电话
	private int  role;     //角色
	private Timestamp createTime;//申请时间
	private String realname;//真实姓名
	private String email;
	private int status;//用户状态

	public User() {
	}

	public User(int id, String username,
				String password, String phone,
				int role, Timestamp createTime,
				String realname, String email, int status) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.createTime = createTime;
		this.realname = realname;
		this.email = email;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", role='" + role + '\'' +
				", createTime=" + createTime +
				", realname='" + realname + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				'}';
	}
}
