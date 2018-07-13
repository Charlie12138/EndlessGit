package com.liqinglin.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.DbUtil;

public class CartDao {
	
	/**
	 * 为用户添加购物车
	 */
	public void addCart(int userId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into t_shopcart (userId) values (?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
	}
	
	/**
	 * 检查用户是否已有购物车
	 */
	public boolean isHasCart(int userId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from t_shopcart where userId = ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, userId);
			if(pstmt.executeQuery().next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	
	/**
	 * 获得购物车
	 */
	public Cart getCartByUserId(int userId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		Cart cart = new Cart();
		String sql = "select * from t_shopcart where userId = ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				cart.setId(rs.getInt("id"));
				cart.setUser(new UserService().getUserInfo(userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return cart;
	}
	
	
	
	
	/**
	 * 检查某个商品是否存在购物车
	 */
	public boolean isExist(CartInfo cartInfo) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		boolean check = false;
		String sql = "select * from t_cartInfo where cuisineId = ? and cartId = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartInfo.getCuisine().getId());
			pstmt.setInt(2, cartInfo.getCart().getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		return check;
	}
	
	/**
	 * 为购物车添加信息
	 */
	public void addCartInfo(CartInfo cartInfo) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into t_cartinfo (storeId, cuisineId, number, totalPrice, cartId) values (?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartInfo.getStore().getStoreId());
			pstmt.setInt(2, cartInfo.getCuisine().getId());
			pstmt.setInt(3, 1);
			pstmt.setDouble(4, cartInfo.getCuisine().getPrice());
			pstmt.setInt(5, cartInfo.getCart().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
	}
	
	
	/**
	 * 获得数目
	 */
	
	public int getCartInfoNum(int cartId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		int totalRecord = 0;
		String sql = "select count(*) from t_cartInfo where cartId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartId);
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
	 * 获得购物车信息（分页）
	 */
	public List<CartInfo> getCartInfos(int cartId, int startIndex, int pageSize) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		
		List<CartInfo> cartInfos = new ArrayList<CartInfo>();
		String sql = "select * from t_cartinfo where cartId = ? limit ?, ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, cartId);
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				CartInfo cartInfo = new CartInfo();
				cartInfo.setId(rs.getInt("id"));
				cartInfo.setCart(new CartService().getCart(rs.getInt("cartId")));
				cartInfo.setCuisine(new StoreService().getCuisineInfo(rs.getInt("cuisineId")));
				cartInfo.setNumber(rs.getInt("number"));
				cartInfo.setStore(new StoreService().getStoreInfo(rs.getInt("storeId")));
				cartInfo.setTotalPrice(rs.getDouble("totalPrice"));
				cartInfos.add(cartInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return cartInfos;
	}
	
	
	/**
	 * 获得购物车信息
	 */
	public List<CartInfo> getCartInfos(int cartId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		
		List<CartInfo> cartInfos = new ArrayList<CartInfo>();
		String sql = "select * from t_cartinfo where cartId = ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, cartId);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				CartInfo cartInfo = new CartInfo();
				cartInfo.setId(rs.getInt("id"));
				cartInfo.setCart(new CartService().getCart(rs.getInt("cartId")));
				cartInfo.setCuisine(new StoreService().getCuisineInfo(rs.getInt("cuisineId")));
				cartInfo.setNumber(rs.getInt("number"));
				cartInfo.setStore(new StoreService().getStoreInfo(rs.getInt("storeId")));
				cartInfo.setTotalPrice(rs.getDouble("totalPrice"));
				cartInfos.add(cartInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return cartInfos;
	}
	
	/**
	 * 获得同一个购物车的同一个店铺的信息
	 */
	public List<CartInfo> getCartInfos(int cartId, int storeId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
	
		List<CartInfo> cartInfos = new ArrayList<CartInfo>();
		String sql = "select * from t_cartinfo where cartId = ? and storeId = ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, cartId);
			pstmt.setInt(2, storeId);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {	
				CartInfo cartInfo = new CartInfo();
				cartInfo.setId(rs.getInt("id"));
				cartInfo.setCart(new CartService().getCart(rs.getInt("cartId")));
				cartInfo.setCuisine(new StoreService().getCuisineInfo(rs.getInt("cuisineId")));
				cartInfo.setNumber(rs.getInt("number"));
				cartInfo.setStore(new StoreService().getStoreInfo(rs.getInt("storeId")));
				cartInfo.setTotalPrice(rs.getDouble("totalPrice"));
				cartInfos.add(cartInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return cartInfos;
	}
	
	/**
	 * 获得购物车某个商品信息
	 */
	public CartInfo getCartInfo(CartInfo cartInfo) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from t_cartinfo where cartId = ? and cuisineId = ?";
		try {
			pstmt = con .prepareStatement(sql);
			pstmt.setInt(1, cartInfo.getCart().getId());
			pstmt.setInt(2, cartInfo.getCuisine().getId());
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				cartInfo.setId(rs.getInt("id"));
				cartInfo.setCuisine(new StoreService().getCuisineInfo(rs.getInt("cuisineId")));
				cartInfo.setNumber(rs.getInt("number"));
				cartInfo.setStore(new StoreService().getStoreInfo(rs.getInt("storeId")));
				cartInfo.setTotalPrice(rs.getDouble("totalPrice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DbUtil.close(pstmt, con);
		}
		return cartInfo;
	}
	
	
	/**
	 * 修改购物车某商品的数量和总价格
	 */
	public void modifyNumPrice(CartInfo cartInfo) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update t_cartinfo set number = ?, totalPrice = ? where cartId = ? and cuisineId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartInfo.getNumber() + 1);
			pstmt.setDouble(2, cartInfo.getTotalPrice() + cartInfo.getCuisine().getPrice());
			pstmt.setInt(3, cartInfo.getCart().getId());
			pstmt.setInt(4, cartInfo.getCuisine().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt, con);
		}
		
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart(int cartId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from t_cartInfo where cartId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
