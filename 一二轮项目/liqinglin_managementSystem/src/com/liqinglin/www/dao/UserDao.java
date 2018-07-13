package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.DbConnection;
import com.liqinglin.www.util.Msg;

public class UserDao {
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 学生登录
	 */
	public Msg studentLogin(String userName) { // Msg是 传递信息和对象 的工具
		try {
			String sql = "select * from t_student WHERE userName = ?";
			con = DbConnection.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			/**
			 * 判断密码是否和数据库里的相同
			 */
			while (rs.next()) {
				return new Msg(rs.getString("passWord"), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.NOUSER, null);
	}

	/**
	 * 教师登录
	 */
	public Msg teacherLogin(String userName) {
		try {
			String sql = "select * from t_teacher WHERE userName = ?";
			con = DbConnection.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return new Msg(rs.getString("passWord"), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.NOUSER, null);
	}

}
