package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.util.DbConnection;

public class CommentDao {
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 设置评论
	 * 
	 * @param userName
	 * @return
	 */
	public Map<String, String> comment(String userName) {
		try {
			Map<String, String> map = new HashMap<String, String>();
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
			 * 将科目和评论放进map
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
					String sql2 = "select *from t_comment where studentId = ? and courseId = ?";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setInt(1, sid);
					pstmt2.setInt(2, list.get(i));
					ResultSet rs2 = pstmt2.executeQuery();
					while (rs2.next()) {
						map.put(rs.getString("courseName"), rs2.getString("comment"));
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
