package com.liqinglin.www.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.po.Student;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.DbConnection;
import com.liqinglin.www.util.Msg;

public class StudentDao {
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return
	 */
	public Msg addStudent(Student student) {
		try {
			con = DbConnection.getCon();
			/**
			 * 检查id是否已经存在
			 */
			String sql = "select * from t_student WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt.close();
				return new Msg(Constants.IDEXISTED, null);
			}
			/**
			 * 添加学生
			 */
			sql = "insert into t_student (id, userName, passWord, name, sex, emergencyContactNum, email, grade, Class) values (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getUserName());
			pstmt.setString(3, student.getPassWord());
			pstmt.setString(4, student.getName());
			pstmt.setString(5, student.getSex());
			pstmt.setString(6, student.getEmergencyContactNum());
			pstmt.setString(7, student.getEmail());
			pstmt.setString(8, student.getGrade());
			pstmt.setString(9, student.getStuClass());
			pstmt.executeLargeUpdate();
			return new Msg(Constants.ADDSUCCEED, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.ADDFAILED, null);
	}

	/**
	 * 删除学生
	 */
	public Msg deleteStudent(String str) {
		try {
			PreparedStatement pstmt;
			con = DbConnection.getCon();
			String sql = "select * from t_student WHERE name = ?";
			PreparedStatement pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, str);
			ResultSet rs = pstmt2.executeQuery();
			while (rs.next()) {
				sql = "delete from t_student where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("id"));
				pstmt.executeUpdate();
				pstmt2.close();
				return new Msg(Constants.DELETESUCCEED, null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}

		return new Msg(Constants.NOSTUDENT, null);
	}

	/**
	 * 学生查看信息
	 */
	public Msg watchInfo(Student student) {
		String sql = "select * from t_student where userName = ?";
		try {
			con = DbConnection.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getUserName());
			ResultSet rs = pstmt.executeQuery();
			/**
			 * 从数据库导出给student
			 */
			while (rs.next()) {
				if (student.getPassWord().equals(rs.getString("passWord"))) {
					student.setId(rs.getInt("id"));
					student.setUserName(rs.getString("userName"));
					student.setName(rs.getString("name"));
					student.setSex(rs.getString("sex"));
					student.setEmergencyContactNum(rs.getString("emergencyContactNum"));
					student.setEmail(rs.getString("email"));
					student.setGrade(rs.getString("grade"));
					student.setStuClass(rs.getString("Class"));
					return new Msg(null, student);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(null, student);
	}

	/**
	 * 搜索学生
	 * 
	 * @param name
	 * @return
	 */
	public List<Student> searchStudent(String name) {
		try {
			con = DbConnection.getCon();
			String sql = "select * from t_student where name like ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			List<Student> list = new ArrayList<Student>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setUserName(rs.getString("userName"));
				student.setPassWord(rs.getString("passWord"));
				student.setName(rs.getString("name"));
				student.setEmergencyContactNum(rs.getString("emergencyContactNum"));
				student.setEmail(rs.getString("email"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getString("sex"));
				student.setStuClass(rs.getString("Class"));
				list.add(student);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return null;
	}

	/**
	 * 先找到要修改的学生
	 */
	public Msg modifyStudentStep1(String name) {
		Student student;
		try {
			con = DbConnection.getCon();
			String sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setUserName(rs.getString("userName"));
				student.setPassWord(rs.getString("passWord"));
				student.setName(rs.getString("name"));
				student.setEmergencyContactNum(rs.getString("emergencyContactNum"));
				student.setEmail(rs.getString("email"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getString("sex"));
				student.setStuClass(rs.getString("Class"));
				return new Msg(Constants.SEARCHSUCCEED, student);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.NOSTUDENT, null);
	}

	/**
	 * 修改学生信息
	 */
	public Msg modifyStudentStep2(Student student) {
		try {
			con = DbConnection.getCon();
			String sql = "update t_student set userName = ?, passWord = ?, name = ?, "
					+ "sex = ?, emergencyContactNum = ?, email = ?, grade = ?, Class = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getUserName());
			pstmt.setString(2, student.getPassWord());
			pstmt.setString(3, student.getName());
			pstmt.setString(4, student.getSex());
			pstmt.setString(5, student.getEmergencyContactNum());
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getGrade());
			pstmt.setString(8, student.getStuClass());
			pstmt.setInt(9, student.getId());
			pstmt.executeUpdate();
			DbConnection.close(pstmt, con);
			return new Msg(Constants.MODIFYSUCCEED, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Constants.MODIFYFAILED, null);
	}

	/**
	 * 查看个人成绩
	 * 
	 * @return
	 */
	public Map<String, Double> watchScore(String userName) {
		try {
			Map<String, Double> map = new HashMap<String, Double>();
			con = DbConnection.getCon();
			/**
			 * 获得学生的id
			 */
			String sql = "select * from t_student where userName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			int sid = 0;// 初始化学生id
			while (rs.next()) {
				sid = rs.getInt("id");
			}
			pstmt.close();
			/**
			 * 获得该学生所有科目id
			 */
			sql = "select * from t_score where studentId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt("courseId"));
			}
			pstmt.close();
			System.out.println(list.size());
			/**
			 * 将科目和成绩放进map
			 */
			pstmt = null;
			for (int i = 0; i < list.size(); i++) {
				if (pstmt != null)
					pstmt.close();
				sql = "select * from t_course where courseId  = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, list.get(i));
				rs = pstmt.executeQuery();
				PreparedStatement pstmt2 = null;
				while (rs.next()) {
					if (pstmt2 != null)
						pstmt2.close();
					String sql2 = "select *from t_score where studentId = ? and courseId = ?";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setInt(1, sid);
					pstmt2.setInt(2, list.get(i));
					ResultSet rs2 = pstmt2.executeQuery();
					while (rs2.next()) {
						map.put(rs.getString("courseName"), rs2.getDouble("score"));
					}
				}
			}
			if (pstmt != null) {
				pstmt.close();
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
