package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.DbUtil;

public class UserDao {
	public int addUser(User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			/**
			 * 用户表添加信息
			 */
			String sql = "insert into t_user(username, password, status, createTime) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, 0);
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			result = pstmt.executeUpdate();
			pstmt.close();

			/**
			 * 从用户表找到用户id
			 */
			int userId = 0;
			if (result == 1) {
				sql = "select * from t_user where username = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					userId = rs.getInt("id");
				}
			}

			/**
			 * 用户_角色 表添加信息
			 */
			sql = "insert into t_user_role(userId, roleId) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, 1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 获得用户信息
	 */
	public User getUserInfo(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		User user = new User();
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from t_user where username=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setCreateTime(rs.getTimestamp("createTime"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return user;
	}
	
	/**
	 * 获得用户信息
	 */
	public User getUserInfo(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		User user = new User();
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from t_user where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setCreateTime(rs.getTimestamp("createTime"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return user;
	}
	
	
	
	
	/**
	 * 检查用户名是否已经注册
	 */
	public boolean usernameIsExist(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from t_user where username=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			// 判断是否存在该用户

			if (pstmt.executeQuery().next()) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return false;
	}

	/**
	 * 登录
	 */
	public int login(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			if (!checkRole(user)) {
				return Contants.ROLE_SELECT_ERROR_CODE;
			}
			String sql = "select * from t_user where username=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("status") == 1) {
					return Contants.SUCCESS_CODE;
				} else {
					return Contants.NOT_EXAMINE_CODE;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return Contants.FAIL_CODE;
	}

	/**
	 * 检查权限
	 */
	public boolean checkRole(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			/**
			 * 从用户表找到用户id
			 */
			int userId = 0;
			String sql = "select * from t_user where username= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("id");
			}
			pstmt.close();

			/**
			 * 查找这个用户是否有某权限
			 */
			String role = user.getRole();

			sql = "select * from t_user_role where userId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("roleId") == Integer.parseInt(role)
						|| (rs.getInt("roleId") == Integer.parseInt(Contants.ROLE_MERCHANT)
								&& role.equals(Contants.ROLE_USER))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return false;

	}

	/**
	 * 用户开店信息录入数据库
	 */
	public int openStore(Store store) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "select * from t_user where username = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store.getUsername());
			ResultSet rs = pstmt.executeQuery();
			int userId = 0;
			if (rs.next()) {
				userId = rs.getInt("id");
			}
			pstmt.close();
			sql = "insert into t_store (storename, shopkeeperName, address, phone, storeDescription, shopkeeperId, createTime, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store.getStoreName());
			pstmt.setString(2, store.getShopkeeperName());
			pstmt.setString(3, store.getAddress());
			pstmt.setString(4, store.getPhone());
			pstmt.setString(5, store.getStoreDescription());
			pstmt.setInt(6, userId);
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(8, 0);// 0表示审核未通过
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 检查店铺名是否已经注册
	 */
	public boolean storeNameIsExist(String storeName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			String sql = "select * from t_store where storeName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeName);
			// 判断是否存在该用户

			if (pstmt.executeQuery().next()) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return false;
	}

	/**
	 * 得到用户所拥有的店铺
	 */
	public List<Store> getUserStore(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Store> stores = new ArrayList<Store>();
		int userId = 0;
		try {
			conn = DbUtil.getConnection();

			/**
			 * 获得用户的id
			 */
			String sql = "select * from t_user where username=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("id");
			}
			pstmt.close();

			/**
			 * 获得用户名下的店铺
			 */
			sql = "select * from t_store where shopkeeperId = ? and status = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, 1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				store.setStatus(1);
				store.setStoreName(rs.getString("storename"));
				store.setShopkeeperName(rs.getString("shopkeeperName"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setCreateTime(rs.getTimestamp("createTime"));
				store.setShopkeeperId(userId);
				store.setStoreDescription(rs.getString("storeDescription"));
				store.setUsername(username);
				store.setStoreId(rs.getInt("id"));
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return stores;
	}
	
	/**
	 * 修改用户信息
	 */
	public boolean modifyUserInfo(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "update t_user set realname = ?,phone = ?, email = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getRealname());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getId());		
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return result;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
