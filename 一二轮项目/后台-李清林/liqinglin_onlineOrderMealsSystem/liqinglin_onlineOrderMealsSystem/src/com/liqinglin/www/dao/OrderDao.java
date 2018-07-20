package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.DbUtil;

public class OrderDao {
	
	/**
	 * 添加整个订单
	 */
	public int addTotalOrder(TotalOrder totalOrder) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into t_totalorder (orderNum, receiver, address, phone, message, createTime, orderStatus, userId, storeId, totalPrice) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, totalOrder.getOrderNum());
			pstmt.setString(2, totalOrder.getReceiver());
			pstmt.setString(3, totalOrder.getAddress());
			pstmt.setString(4, totalOrder.getPhone());
			pstmt.setString(5, totalOrder.getMessage());
			pstmt.setTimestamp(6, totalOrder.getCreateTime());
			pstmt.setInt(7, Contants.ORDER_STATUS_A);
			pstmt.setInt(8, totalOrder.getUserId());
			pstmt.setInt(9, totalOrder.getStoreId());
			pstmt.setDouble(10, totalOrder.getTotalPrice());
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
	 * 删除订单
	 */
	public void deleteOrder(int orderId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from t_totalorder where id =" + orderId;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获得总订单
	 */
	public TotalOrder getTotalOrder(String orderNum) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		TotalOrder totalOrder = new TotalOrder();
		String sql = "select *from t_totalorder where orderNum = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				totalOrder.setAddress(rs.getString("address"));
				totalOrder.setCreateTime(rs.getTimestamp("createTime"));
				totalOrder.setId(rs.getInt("id"));
				totalOrder.setMessage(rs.getString("message"));
				totalOrder.setOrderNum(rs.getString("orderNum"));
				totalOrder.setPhone(rs.getString("phone"));
				totalOrder.setReceiver(rs.getString("receiver"));
				totalOrder.setStatus(rs.getInt("orderStatus"));
				totalOrder.setStoreId(rs.getInt("storeId"));
				totalOrder.setTotalPrice(rs.getDouble("totalPrice"));
				totalOrder.setUserId(rs.getInt("userId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return totalOrder;
	}
	
	/**
	 * 添加单个商品订单
	 */
	public int addSingleOrder(SingleOrder singleOrder) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into t_singleorder (orderId, storeId, cuisineId, number, totalprice) values (?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, singleOrder.getOrderId());
			pstmt.setInt(2, singleOrder.getStoreId());
			pstmt.setInt(3, singleOrder.getCuisineId());
			pstmt.setInt(4, singleOrder.getNumber());
			pstmt.setDouble(5, singleOrder.getTotalPrice());
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
	 * 获得订单中单个商品订单的总数
	 */
	public int getStoreAllSingleOrderNum(int orderId){
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_singleorder where orderId = " + orderId;
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
	 * 分页：获得某一页所需要的单个商品订单数据
	 */
	public List<SingleOrder> getPageSingleOrder(int orderId, int startIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<SingleOrder> singleOrders = new ArrayList<SingleOrder>();
		conn = DbUtil.getConnection();
		String sql = "select * from t_singleorder where orderId = ? limit ?, ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SingleOrder singleOrder = new SingleOrder();
				singleOrder.setOrderId(orderId);
				singleOrder.setCuisineId(rs.getInt("cuisineId"));
				singleOrder.setNumber(rs.getInt("number"));
				singleOrder.setStoreId(rs.getInt("storeId"));
				singleOrder.setTotalPrice(rs.getDouble("totaLPrice"));
				singleOrders.add(singleOrder);
			}
			pstmt.close();

			sql = "select * from t_cuisine where id = ?";
			pstmt = conn.prepareStatement(sql);
			for(SingleOrder single : singleOrders) {
				pstmt.setInt(1, single.getCuisineId());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					single.setCuisineName(rs.getString("cuisineName"));
					single.setSinglePrice(rs.getDouble("price"));
				}				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return singleOrders;
	}
	
	/**
	 * 修改订单状态
	 */
	public void modifyOrderStatus(int status, int id) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update t_totalorder set orderStatus = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		
	}
	
	/**
	 * 获得店铺符合要求订单的数量
	 */
	public int getStoreOrderCount(int status, int id, int roleSelect){
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_totalorder where storeId = ? and orderStatus = ?";
		String sql2 = "select count(*) from t_totalorder where userId = ? and orderStatus =?";
		try {
			if(roleSelect == Contants.STORE_ORDER) {  //同一个店铺的订单
				pstmt = con.prepareStatement(sql);
			} else if(roleSelect == Contants.USER_ORDER) { //同一个用户的订单
				pstmt = con.prepareStatement(sql2);
			}
			pstmt.setInt(1, id);
			pstmt.setInt(2, status);
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
	 * 分页：获得某一页所需要的订单数据
	 */
	public List<TotalOrder> getPageOrder(int status, int id, int startIndex, int pageSize, int roleSelect) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<TotalOrder> totalOrders = new ArrayList<TotalOrder>();
		conn = DbUtil.getConnection();
		String sql = "select * from t_totalorder where storeId = ? and orderStatus = ? limit ?, ?";
		String sql2 = "select * from t_totalorder where userId = ? and orderStatus = ? limit ?, ?";
		try {
			if(roleSelect == Contants.STORE_ORDER) {  //获得同一个店铺的订单
				pstmt = conn.prepareStatement(sql);
			} else if(roleSelect == Contants.USER_ORDER) { //获得同一个用户的订单
				pstmt = conn.prepareStatement(sql2);
			}
			pstmt.setInt(1, id);
			pstmt.setInt(2, status);
			pstmt.setInt(3, startIndex);
			pstmt.setInt(4, pageSize);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TotalOrder totalOrder = new TotalOrder();
				totalOrder.setAddress(rs.getString("address"));
				totalOrder.setCreateTime(rs.getTimestamp("createTime"));
				totalOrder.setId(rs.getInt("id"));
				totalOrder.setMessage(rs.getString("message"));
				totalOrder.setOrderNum(rs.getString("orderNum"));
				totalOrder.setPhone(rs.getString("phone"));
				totalOrder.setReceiver(rs.getString("receiver"));
				totalOrder.setStatus(rs.getInt("orderStatus"));
				totalOrder.setStoreId(rs.getInt("storeId"));
				totalOrder.setTotalPrice(rs.getDouble("totalPrice"));
				totalOrder.setUserId(rs.getInt("userId"));
				totalOrders.add(totalOrder);				
			}
			pstmt.close();

			sql = "select * from t_store where id = ?";
			pstmt = conn.prepareStatement(sql);
			for(TotalOrder total : totalOrders) {
				pstmt.setInt(1, total.getStoreId());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					total.setStoreName(rs.getString("storename"));
				}				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, conn);
		}
		return totalOrders;
	}
	
	
	
}
