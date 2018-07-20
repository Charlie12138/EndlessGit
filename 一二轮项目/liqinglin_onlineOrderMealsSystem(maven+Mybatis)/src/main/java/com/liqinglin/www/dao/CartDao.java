package com.liqinglin.www.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;

import com.liqinglin.www.util.CartDaoContants;
import com.liqinglin.www.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CartDao {

	/**
	 * 为用户添加购物车
	 * @param userId
	 */
	public void addCart(int userId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.ADDCART;
		session.insert(statement, userId);
		session.close();
	}

	
	/**
	 * 检查用户是否已有购物车
	 */
	public int isHasCart(int userId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.ISHASCART;
		int result = session.selectOne(statement, userId);
		session.close();
		return  result;
	}

	/**
	 * 获得购物车
	 * @param userId
	 * @return
	 */
	public Cart getCartByUserId(int userId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_CART_BY_USERID;
		Cart cart = session.selectOne(statement, userId);
		session.close();
		return  cart;
	}


	/**
	 * 检查某个商品是否存在购物车
	 * @param cartInfo
	 * @return
	 */
	public int isExist(CartInfo cartInfo) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.ISEXIST;
		int result = session.selectOne(statement, cartInfo);
		session.close();
		return result;
	}

	/**
	 * 为购物车添加信息
	 * @param cartInfo
	 */

	public void addCartInfo(CartInfo cartInfo) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.ADDCARTINFO;
		cartInfo.setNumber(1);
		cartInfo.setTotalPrice(cartInfo.getCuisine().getPrice());
		session.insert(statement, cartInfo);
		session.close();
	}


	/**
	 * 获得数目
	 * @param cartId
	 * @return
	 */

	public int getCartInfoNum(int cartId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_CARTINFO_NUM;
		int result = session.selectOne(statement, cartId);
		session.close();
		return  result;
	}


	/**
	 * 获得购物车信息（分页）
	 * @param cartId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CartInfo> getCartInfos(int cartId, int startIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cartId", cartId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_PAGE_CARTINFO;
		List<CartInfo> cartInfos = session.selectList(statement, map);
		return cartInfos;
	}

	/**
	 * 获得购物车信息
	 * @param cartId
	 * @return
	 */
	public List<CartInfo> getCartInfos(int cartId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_CARTINFO;
		List<CartInfo> cartInfos = session.selectList(statement, cartId);
		return cartInfos;
	}

	/**
	 * 获得同一个购物车的同一个店铺的信息
	 * @param cartInfo
	 * @return
	 */
	public List<CartInfo> getCartInfos(CartInfo cartInfo) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_CARTINFO_STORE;
		List<CartInfo> cartInfos = session.selectList(statement, cartInfo);
		return cartInfos;
	}

	/**
	 * 获得购物车某个商品信息
	 * @param cartInfo
	 * @return
	 */

	public CartInfo getCartInfo(CartInfo cartInfo) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.GET_CARTINFO_BY_CUISINE;
		cartInfo = session.selectOne(statement, cartInfo);
		return cartInfo;
	}


	/**
	 * 修改购物车某商品的数量和总价格
	 * @param cartInfo
	 */

	public void modifyNumPrice(CartInfo cartInfo) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.MODIFYNUMPRICE;
		cartInfo.setNumber(cartInfo.getNumber() + 1);
		cartInfo.setTotalPrice( cartInfo.getTotalPrice() + cartInfo.getCuisine().getPrice());
		int result = session.update(statement, cartInfo);
		session.close();
	}


	/**
	 * 清空购物车
	 * @param cartId
	 */
	public void clearCart(int cartId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = CartDaoContants.CLEAR_CART;
		session.delete(statement, cartId);
		session.close();
	}
}
