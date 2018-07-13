package com.liqinglin.www.service;

import com.liqinglin.www.util.ValidateUtil;

import java.util.List;
import java.util.Map;

import com.liqinglin.www.dao.StudentDao;
import com.liqinglin.www.po.Student;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class StudentService {
	private StudentDao studentDao = new StudentDao();

	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return
	 */
	public Msg addStudent(Student student) {
		if (ValidateUtil.isInvalidString(student.getUserName())) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getPassWord())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getName())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getSex())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getEmergencyContactNum())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getEmail())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getGrade())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(student.getStuClass())) {
			return new Msg(Constants.NULL, null);
		} else {
			Msg msg = studentDao.addStudent(student);
			return msg;
		}
	}

	/**
	 * 删除学生
	 * 
	 * @param str
	 * @return
	 */
	public Msg deleteStudent(String str) {
		if (ValidateUtil.isInvalidString(str)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		}
		Msg msg = studentDao.deleteStudent(str);
		return msg;
	}

	/**
	 * 搜索学生
	 * 
	 * @param studentName
	 * @return
	 */
	public List<Student> searchStudent(String studentName) {
		System.out.println(studentName);
		if (ValidateUtil.isInvalidString(studentName)) { // 判断是否为空
			return null;
		} else if (studentName.equals("%"))
			studentName = "不能输入%"; // 避免因为输入%而输出所有数据
		List<Student> list = (List<Student>) studentDao.searchStudent(studentName);

		if (list.toString() != "[]") {
			return list;
		}
		return null;
	}

	/**
	 * 修改学生信息
	 * 
	 * @param name
	 * @return
	 */
	public Msg modifyStep1(String name) {
		if (ValidateUtil.isInvalidString(name)) {
			return new Msg(Constants.NULL, null);
		}
		Msg msg = studentDao.modifyStudentStep1(name);
		return msg;
	}

	public Msg modifyStep2(Student student) {
		return studentDao.modifyStudentStep2(student);
	}

	/**
	 * 查看个人信息
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Msg check(String userName, String passWord) {
		Student student = new Student();
		if (ValidateUtil.isInvalidString(userName)) {
			return new Msg(Constants.NULL, null);
		}
		student.setUserName(userName);
		student.setPassWord(passWord);
		Msg msg = studentDao.watchInfo(student);
		return msg;
	}

	/**
	 * 查看成绩
	 * 
	 * @param userName
	 * @return
	 */
	public Map<String, Double> watchScore(String userName) {
		if (ValidateUtil.isInvalidString(userName)) {
			return null;
		}
		Map<String, Double> map = studentDao.watchScore(userName);
		return map;
	}
}
