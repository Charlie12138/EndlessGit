package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liqinglin.www.po.Score;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.DbConnection;
import com.liqinglin.www.util.Msg;

public class ScoreDao {
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * 设置成绩
	 * 
	 * @param name
	 * @param courseName
	 * @param score
	 * @param comment
	 * @return
	 */
	public Msg setScore(String name, String courseName, double score, String comment) {
		try {
			con = DbConnection.getCon();
			/**
			 * 检查是否有这个学生
			 */
			String sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			System.out.println(name);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOSTUDENT, null);
			}
			pstmt.close();
			/**
			 * 检查是否有这个科目
			 */
			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			System.out.println(courseName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOCOURSE, null);
			}
			pstmt.close();
			/**
			 * 获得学生和课程id
			 */
			sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			int sid = 0;
			while (rs.next()) {
				sid = rs.getInt("id");
			}
			pstmt.close();

			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			rs = pstmt.executeQuery();
			int cid = 0;
			while (rs.next()) {
				cid = rs.getInt("courseId");
			}
			pstmt.close();
			/**
			 * 检查是否某个学生的某科成绩已经存在
			 */
			sql = "select *from t_score where studentId = ? and courseId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt.close();
				return new Msg(Constants.SCOREEXISTED, null);
			}
			pstmt.close();
			/**
			 * 设置成绩
			 */
			sql = "insert into t_score (studentId, courseId, score) values (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			pstmt.setDouble(3, score);
			pstmt.executeUpdate();
			pstmt.close();
			/**
			 * 设置评论
			 */
			sql = "insert into t_comment (studentId, courseId, comment) values (?, ?, ?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			pstmt.setString(3, comment);
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
	 * 删除成绩
	 * 
	 * @param name
	 * @param courseName
	 * @return
	 */
	public Msg deleteScore(String name, String courseName) {
		try {
			con = DbConnection.getCon();
			/**
			 * 检查是否有这个学生
			 */
			String sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			System.out.println(name);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOSTUDENT, null);
			}
			pstmt.close();
			/**
			 * 检查是否有这个科目
			 */
			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			System.out.println(courseName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOCOURSE, null);
			}
			pstmt.close();
			/**
			 * 获得学生和课程id
			 */
			sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			int sid = 0;
			while (rs.next()) {
				sid = rs.getInt("id");
			}
			pstmt.close();

			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			rs = pstmt.executeQuery();
			int cid = 0;
			while (rs.next()) {
				cid = rs.getInt("courseId");
			}
			pstmt.close();
			/**
			 * 检查是否已经删除
			 */
			sql = "select *from t_score where studentId = ? and courseId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			while (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.DELETED, null);
			}
			/**
			 * 开始删除
			 */
			sql = "delete from t_score where studentId = ? and courseId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			pstmt.executeUpdate();
			return new Msg(Constants.DELETESUCCEED, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}

		return new Msg(Constants.DELETEFAILED, null);
	}

	/**
	 * 搜索成绩
	 * 
	 * @param name
	 * @param courseName
	 * @return
	 */
	public Msg searchScore(String name, String courseName) { // 避免因为输入%而输出所有数据
		try {
			con = DbConnection.getCon();
			/**
			 * 检查是否有这个学生
			 */
			String sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			System.out.println(name);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOSTUDENT, null);
			}
			pstmt.close();
			/**
			 * 检查是否有这个科目
			 */
			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			System.out.println(courseName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt.close();
				return new Msg(Constants.NOCOURSE, null);
			}
			pstmt.close();
			/**
			 * 获得学生和课程id
			 */
			sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			int sid = 0;
			while (rs.next()) {
				sid = rs.getInt("id");
			}
			pstmt.close();

			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			rs = pstmt.executeQuery();
			int cid = 0;
			while (rs.next()) {
				cid = rs.getInt("courseId");
			}
			pstmt.close();
			System.out.println(sid + cid);
			/**
			 * 查询成绩
			 */
			sql = "select * from t_score where studentId = ? and courseId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setStudentName(name);
				score.setCourseName(courseName);
				score.setScore(rs.getInt("score"));
				return new Msg(Constants.SEARCHSUCCEED, score);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnection.close(pstmt, con);
		}
		return new Msg(Constants.SEARCHFAILED, null);
	}

	/**
	 * 修改成绩
	 * 
	 * @param score
	 * @return
	 */
	public Msg modifyScore(Score score) {
		try {
			con = DbConnection.getCon();
			/**
			 * 获得学生和课程id
			 */
			String sql = "select * from t_student where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, score.getStudentName());
			ResultSet rs = pstmt.executeQuery();
			int sid = 0;
			while (rs.next()) {
				sid = rs.getInt("id");
			}
			pstmt.close();

			sql = "select *from t_course where courseName = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, score.getCourseName());
			rs = pstmt.executeQuery();
			int cid = 0;
			while (rs.next()) {
				cid = rs.getInt("courseId");
			}
			pstmt.close();

			/**
			 * 修改成绩
			 */
			sql = "update t_score set score = ? where studentId = ? and courseId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, score.getScore());
			pstmt.setInt(2, sid);
			pstmt.setInt(3, cid);
			pstmt.executeUpdate();
			return new Msg(Constants.MODIFYSUCCEED, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Msg(Constants.MODIFYFAILED, null);
	}

}
