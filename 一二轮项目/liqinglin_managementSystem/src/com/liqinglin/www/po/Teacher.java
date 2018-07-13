package com.liqinglin.www.po;

public class Teacher {
	private int id;
	private String userName;
	private String passWord;
	private String name;
	private String grade;
	private String teaClass;
	private String callNumber;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTeaClass() {
		return teaClass;
	}

	public void setTeaClass(String teaClass) {
		this.teaClass = teaClass;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Teacher() {

	}

	public Teacher(int id, String userName, String passWord, String name, String grade, String teaClass,
			String callNumber, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
		this.grade = grade;
		this.teaClass = teaClass;
		this.callNumber = callNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", name=" + name + ", grade="
				+ grade + ", teaClass=" + teaClass + ", callNumber=" + callNumber + ", email=" + email + "]";
	}
}
