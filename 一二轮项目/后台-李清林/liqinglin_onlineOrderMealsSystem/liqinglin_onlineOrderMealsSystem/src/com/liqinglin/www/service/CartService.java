package com.liqinglin.www.service;


import java.util.List;

import com.liqinglin.www.dao.CartDao;
import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.PageBean;

public class CartService {
	
	CartDao cartDao = new CartDao();
	/**
	 * 为用户添加购物车
	 */
	public void addCart(int userId) {
		if(!cartDao.isHasCart(userId)) {
			cartDao.addCart(userId);
		}
	}
	
	/**
	 * 获得购物车
	 */
	public Cart getCart(int userId) {
		return cartDao.getCartByUserId(userId);
	}
	
	/**
	 * 为购物车添加信息
	 */
	public void addCartInfo(CartInfo cartInfo) {
		if(cartDao.isExist(cartInfo)) {
			cartInfo = cartDao.getCartInfo(cartInfo);			
			cartDao.modifyNumPrice(cartInfo);	
			return;
		}
		cartDao.addCartInfo(cartInfo);
	}
	
	/**
	 * 获得购物车信息
	 */
	public PageBean<CartInfo> getCartInfos(int cartId, int pageNum, int pageSize) {
		int totalRecord = cartDao.getCartInfoNum(cartId);
		PageBean<CartInfo> pb = new PageBean<CartInfo>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();
		pb.setList(cartDao.getCartInfos(cartId, startIndex, pageSize));
		return pb;
	}
	
	/**
	 * 获得同一个购物车信息
	 */
	public List<CartInfo> getCartInfos(int cartId) {
		
		return cartDao.getCartInfos(cartId);
	}
	
	/**
	 * 获得同一个购物车同一个店铺的信息
	 */
	public List<CartInfo> getCartInfos(int cartId, int storeId) {
		
		return cartDao.getCartInfos(cartId, storeId);
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart(int cartId) {
		cartDao.clearCart(cartId);
	}
	
	
	
}

