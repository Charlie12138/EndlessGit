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
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.DbUtil;

public class StoreDao {
	/**
	 * 获得某个店铺的信息
	 */
	public Store getStoreInfoById(int storeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Store store = new Store();
		String sql = "select *from t_store where id = ?";
		con = DbUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, storeId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				store.setStatus(rs.getInt("status"));
				store.setStoreName(rs.getString("storename"));
				store.setShopkeeperName(rs.getString("shopkeeperName"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setCreateTime(rs.getTimestamp("createTime"));
				store.setShopkeeperId(rs.getInt("shopkeeperId"));
				store.setStoreDescription(rs.getString("storeDescription"));
				store.setStoreId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return store;
	}
	
	public Store getStoreInfoByName(String storeName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Store store = new Store();
		String sql = "select *from t_store where storename = ?";
		con = DbUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, storeName);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				store.setStatus(rs.getInt("status"));
				store.setStoreName(rs.getString("storename"));
				store.setShopkeeperName(rs.getString("shopkeeperName"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setCreateTime(rs.getTimestamp("createTime"));
				store.setShopkeeperId(rs.getInt("shopkeeperId"));
				store.setStoreDescription(rs.getString("storeDescription"));
				store.setStoreId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return store;
	}
	
	/**
	 * 上架美食
	 */
	public int addCuisine(Store store, Cuisine cuisine) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		String sql = "insert into t_cuisine (storeId, cuisineName, price, description, picturePath, status, createTime, sellCount) values (?, ?, ?, ?, ?, ?, ?, ?)";
		con = DbUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store.getStoreId());
			pstmt.setString(2, cuisine.getCuisineName());
			pstmt.setDouble(3, cuisine.getPrice());
			pstmt.setString(4, cuisine.getDescription());
			pstmt.setString(5, cuisine.getPicturePath());
			pstmt.setInt(6, Contants.EXAMINEING);
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(8, 0);//设置销量为零
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return result;
	}
	
	/**
	 * 得到菜肴信息
	 */
	public Cuisine getCuisineInfo(int cuisineId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Cuisine cuisine = new Cuisine();
		String sql = "select *from t_cuisine where id = ?";
		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cuisineId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setPrice(rs.getDouble("price"));	
				cuisine.setId(rs.getInt("id"));
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return cuisine;
	}
	
	
	/**
	 * 修改菜肴信息
	 */
	public boolean updateCuisineInfo(Cuisine cuisine) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "update t_cuisine set cuisineName = ?, price = ?, description = ?, picturePath = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cuisine.getCuisineName());
			pstmt.setDouble(2, cuisine.getPrice());
			pstmt.setString(3, cuisine.getDescription());
			pstmt.setString(4, cuisine.getPicturePath());
			pstmt.setInt(5, cuisine.getId());
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
	
	/**
	 * 修改店铺信息
	 */
	public boolean updateStoreInfo(Store store) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "update t_store set storeName = ?,shopkeeperName = ?, address = ?, phone = ?, storeDescription = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, store.getStoreName());
			pstmt.setString(2, store.getShopkeeperName());
			pstmt.setString(3, store.getAddress());
			pstmt.setString(4, store.getPhone());
			pstmt.setString(5, store.getStoreDescription());
			pstmt.setInt(6, store.getStoreId());
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
	
	/**
	 * 获得所有菜肴的数量
	 */
	public int getAllCuisinesNum() {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_cuisine";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		} 
		return totalRecord;
	}
	
	/**
	 * 分页：获得某一页所需要的数据
	 */
	public List<Cuisine> getPageCuisines(int startIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		conn = DbUtil.getConnection();
		String sql = "select * from t_cuisine limit ?, ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setId(rs.getInt("id"));
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setPrice(rs.getDouble("price"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setStatus(rs.getInt("status"));
				cuisine.setCreateTime(rs.getTimestamp("createTime"));
				cuisine.setStore(getStoreInfoById(rs.getInt("storeId")));
				cuisines.add(cuisine);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return cuisines;
	}
	
	/**
	 * 修改销量
	 */
	public boolean modifySellCount(int cuisineId, int number) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		int sellCount = 0;
		String sql = "select *from t_cuisine where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cuisineId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sellCount = rs.getInt("sellCount");
			}
			pstmt.close();
			sql = "update t_cuisine set sellCount = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sellCount + number);
			pstmt.setInt(2, cuisineId);
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
	
	/**
	 * 获得搜索出的记录数
	 */
	public int getCuisinesNumByCuisineName(String key) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_cuisine where cuisineName like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		} 
		return totalRecord;
	}
	
	public int getCuisinesNumByStoreName(String key) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_cuisine where storeId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, getStoreInfoByName(key).getStoreId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		} 
		return totalRecord;
	}
	
	/**
	 * 分页准备
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Cuisine> searchByCuisine(String key, int startIndex, int pageSize) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from t_cuisine where cuisineName like ? limit ?, ?";
		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setId(rs.getInt("id"));
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setPrice(rs.getDouble("price"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setStatus(rs.getInt("status"));
				cuisine.setCreateTime(rs.getTimestamp("createTime"));
				cuisine.setStore(getStoreInfoById(rs.getInt("storeId")));
				cuisines.add(cuisine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return cuisines;
	}
	
	
	/**
	 * 分页准备
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Cuisine> searchByStore(String key, int startIndex, int pageSize) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from t_cuisine where storeId = ? limit ?, ?";
		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, getStoreInfoByName(key).getStoreId());
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setId(rs.getInt("id"));
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setPrice(rs.getDouble("price"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setStatus(rs.getInt("status"));
				cuisine.setCreateTime(rs.getTimestamp("createTime"));
				cuisine.setStore(getStoreInfoById(rs.getInt("storeId")));
				cuisines.add(cuisine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return cuisines;
	}
	
	
	/**
	 * 得到某个店铺的所有菜肴的数量
	 */
	public int getUserAllCuisines(int storeId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;		
		int totalRecord = 0;
		String sql = "select count(*) from t_cuisine where storeId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, storeId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		} 
		return totalRecord;
	}

	/**
	 * 分页：获得某一页所需要的数据
	 */
	public List<Cuisine> getPageCuisine(int storeId, int startIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		conn = DbUtil.getConnection();
		String sql = "select * from t_cuisine where storeId = ? and status = ? limit ?, ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeId);
			pstmt.setInt(2, Contants.PASS_EXAMINE);
			pstmt.setInt(3, startIndex);
			pstmt.setInt(4, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setId(rs.getInt("id"));
				cuisine.setStoreId(storeId);
				cuisine.setCuisineName(rs.getString("cuisineName"));
				cuisine.setPrice(rs.getDouble("price"));
				cuisine.setDescription(rs.getString("description"));
				cuisine.setPicturePath(rs.getString("picturePath"));
				cuisine.setStatus(rs.getInt("status"));
				cuisine.setCreateTime(rs.getTimestamp("createTime"));
				cuisines.add(cuisine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return cuisines;
	}
}
