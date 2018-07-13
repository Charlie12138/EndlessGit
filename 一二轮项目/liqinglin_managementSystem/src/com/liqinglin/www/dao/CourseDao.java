package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.po.Course;
import com.liqinglin.www.util.*;

public class CourseDao {
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 添加科目
	 */
	public Msg addCourse(Course course) {
		try {
			con = DbConnection.getCon();

			/**
			 * 判断id是否存在
			 */
			String sql = "select * from t_course where courseId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getCourseId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt.close();
				return new Msg(Constants.IDEXISTED, null);
			}
			/**
			 * 判断科目是否已经存在
			 */
			sql = "select * from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course.getCourseName());
			ResultSet rs1 = pstmt.executeQuery();
			if (rs1.next()) {
				pstmt.close();
				return new Msg(Constants.COURSEEXISTED, null);
			}
			/**
			 * 判断有没有这个id老师
			 */
			sql = "select * from t_teacher where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getTeacherId());
			ResultSet rs3 = pstmt.executeQuery();
			if (!rs3.next()) {
				pstmt.close();
				return new Msg(Constants.NOTEACHER, null);
			}
			/**
			 * 添加科目
			 */
			sql = "insert into t_course (courseId, courseName, teacherId) values (?, ?, ?)";
			pstmt.close();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getCourseId());
			pstmt.setString(2, course.getCourseName());
			pstmt.setInt(3, course.getTeacherId());
			pstmt.executeUpdate();
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
	 * 删除科目
	 */
	public Msg deleteCourse(String courseName) {

		try {
			con = DbConnection.getCon();
			String sql = "select * from t_course where courseName = ?";
			PreparedStatement pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, courseName);
			ResultSet rs = pstmt2.executeQuery();
			while (rs.next()) {
				sql = "delete from t_course where courseId = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("courseId"));
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
		return new Msg(Constants.NOCOURSE, null);
	}

	/**
	 * 查询科目
	 */
	public List<Course> searchCourse(String name) {
		try {
			con = DbConnection.getCon();
			String sql = "select * from t_course where courseName like ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			/**
			 * 模糊查询
			 */
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			List<Course> list = new ArrayList<Course>();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				sql = "select * from t_teacher where id = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, rs.getInt("teacherId"));
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					course.setTeacherName(rs2.getString("name"));
				}
				pstmt2.close();
				list.add(course);
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
	 * 修改科目前先找到科目
	 */
	public Msg modifyCourseStep1(String name) {
		Course course;
		try {
			con = DbConnection.getCon();
			String sql = "select * from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				course.setTeacherId(rs.getInt("teacherId"));
				return new Msg(Constants.SEARCHSUCCEED, course);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.NOCOURSE, null);
	}

	/**
	 * 修改科目
	 */
	public Msg modifyCourseStep2(Course course) {
		try {
			con = DbConnection.getCon();
			/**
			 * 判断有没有这个id老师
			 */
			String sql = "select * from t_teacher where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getTeacherId());
			ResultSet rs3 = pstmt.executeQuery();
			if (!rs3.next()) {
				pstmt.close();
				return new Msg(Constants.NOTEACHER, null);
			}
			sql = "update t_course set courseName = ?, teacherId = ? where courseId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course.getCourseName());
			pstmt.setInt(2, course.getTeacherId());
			pstmt.setInt(3, course.getCourseId());
			pstmt.executeUpdate();
			DbConnection.close(pstmt, con);
			return new Msg(Constants.MODIFYSUCCEED, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Constants.MODIFYFAILED, null);
	}

}
