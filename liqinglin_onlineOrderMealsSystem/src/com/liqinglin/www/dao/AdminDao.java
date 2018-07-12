package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.DbUtil;

public class AdminDao {

	/**
	 * 获得正在注册的人
	 * 
	 * @return
	 */
	public List<User> getRegiserUser() {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		List<User> users = new ArrayList<User>();
		try {
			String sql = "select * from t_user where status = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setCreateTime(rs.getTimestamp("createTime"));
				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return users;
	}

	/**
	 * 审核通过正在注册的人
	 */
	public int agreeRegister(User user) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "update t_user set status = ? where username = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, user.getUsername());
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
	 * 获得正在申请开的店铺
	 * 
	 * @return
	 */
	public List<Store> getStores() {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		List<Store> stores = new ArrayList<Store>();
		try {
			String sql = "select * from t_store where status = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Contants.EXAMINEING);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				store.setStoreName(rs.getString("storename"));
				store.setPhone(rs.getString("phone"));
				store.setShopkeeperName(rs.getString("shopkeeperName"));
				store.setAddress(rs.getString("address"));
				store.setStoreDescription(rs.getString("storeDescription"));
				store.setCreateTime(rs.getTimestamp("createTime"));
				stores.add(store);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return stores;
	}

	/**
	 * 对开店请求的操作
	 */
	public int operationOpenStore(String storeName, int status) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "update t_store set status = ? where storename = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setString(2, storeName);
			result = pstmt.executeUpdate();
			pstmt.close();
			/**
			 * 获得用户id
			 */
			sql = "select * from t_store where status = ? and storename = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setString(2, storeName);
			ResultSet rs = pstmt.executeQuery();
			int userId = 0;
			while(rs.next()) {
				userId = rs.getInt("shopkeeperId");
			}
			pstmt.close();
			/**
			 * 设置身份为店家
			 */
			sql = "update t_user_role set roleId = ? where userId = ? and roleId != ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(Contants.ROLE_MERCHANT));
			pstmt.setInt(2, userId);
			pstmt.setInt(3, Integer.parseInt(Contants.ROLE_MERCHANT));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 获得正在申请上架的菜
	 * 
	 * @return
	 */
	public List<Cuisine> getCuisines() {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		try {
			String sql = "select * from t_cuisine where status = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Contants.EXAMINEING);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setId(rs.getInt("id"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setPrice(rs.getDouble("price"));
				cuisine.setCreateTime(rs.getTimestamp("createTime"));

				/**
				 * 获得店铺名
				 */
				sql = "select *from t_store where id = " + rs.getInt("storeId");
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				ResultSet rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					cuisine.setStoreName(rs2.getString("storename"));
					cuisines.add(cuisine);
				}
				pstmt2.close();				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return cuisines;
	}

	/**
	 * 审核通过上架请求
	 */
	public int operationAdd(Cuisine cuisine, int status) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "update t_cuisine set status = ?, createTime = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(3, cuisine.getId());
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
	 * 下架食品
	 */
	public int deleteCuisine(int cuisineId) {
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "delete from t_cuisine where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cuisineId);
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
	 * 分页：获得某一页所需要的数据
	 */
	public List<Store> getPageStores(int startIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Store> stores = new ArrayList<Store>();
		conn = DbUtil.getConnection();
		String sql = "select * from t_store where status != ? limit ?, ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Contants.EXAMINEING);
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				store.setAddress(rs.getString("address"));
				store.setCreateTime(rs.getTimestamp("createTime"));
				store.setPhone(rs.getString("phone"));
				store.setShopkeeperId(rs.getInt("shopkeeperId"));
				store.setShopkeeperName(rs.getString("shopkeeperName"));
				store.setStatus(rs.getInt("status"));
				store.setStoreDescription(rs.getString("storeDescription"));
				store.setStoreId(rs.getInt("id"));
				store.setStoreName(rs.getString("storename"));
				stores.add(store);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return stores;
	}

	/**
	 * 得到所有店铺数量
	 */
	public int getAllStoresNum() {
		int totalRecord = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select count(*) from t_store where status != ?";
		conn = DbUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Contants.EXAMINEING);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalRecord;
	}

}
