package com.liqinglin.www.po;

public class Student {
	private int id;
	private String userName;
	private String passWord;
	private String name;
	private String sex;
	private String emergencyContactNum;
	private String email;
	private String grade;
	private String stuClass;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStuClass() {
		return stuClass;
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmergencyContactNum() {
		return emergencyContactNum;
	}

	public void setEmergencyContactNum(String emergencyContactNum) {
		this.emergencyContactNum = emergencyContactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student() {

	}

	public Student(int id, String uN, String pW, String name, String sex, String eCNum, String email) {
		this.id = id;
		this.userName = uN;
		this.passWord = pW;
		this.name = name;
		this.sex = sex;
		this.emergencyContactNum = eCNum;
		this.email = email;
	}

	public String toString() {
		return "id:" + id + "   " + "userName:" + userName + "   " + "\n" + "name:" + name + "   " + "sex:" + sex
				+ "   " + "emergencyContactNum:" + emergencyContactNum + "\n" + "email:" + email + "   " + "grade:"
				+ grade + "   " + "Class:" + stuClass + "   ";
	}
}
