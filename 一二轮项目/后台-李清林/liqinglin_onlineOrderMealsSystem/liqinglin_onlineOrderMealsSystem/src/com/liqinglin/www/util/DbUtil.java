package com.liqinglin.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DbUtil {

	// 数据库地址
		private static String dbUrl = "jdbc:mysql://localhost:3306/db_ordermealssystem?useSSL=true";

		// 用户名
		private static String dbUserName = "root";

		// 密码
		private static String dbPassword = "my159357@sql";

		// 驱动名称
		private static String jdbcName = "com.mysql.jdbc.Driver";

		/**
		 * 建立数据库接口
		 * 
		 * @return
		 * @throws Exception
		 */
		public static Connection getConnection() {
			try {
				Class.forName(jdbcName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection con = null;
			try {
				con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}

		/**
		 * 关闭数据库
		 * 
		 * @param con
		 */
		public static void close(PreparedStatement pstmt, Connection con) {
			try {
				if (pstmt != null) {
					pstmt.close();
					if (con != null) {
						con.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
