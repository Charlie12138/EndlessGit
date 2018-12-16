package com.liqinglin.www.mapper;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;

import java.util.List;
import java.util.Map;

public interface CartMapper {
	/**
	 * 为用户添加购物车
	 * @param userId
	 */
	void addCart(int userId);

	/**
	 * 检查用户是否已有购物车
	 */
	int isHasCart(int userId);

	/**
	 * 获得购物车
	 * @param userId
	 * @return
	 */
	Cart getCartByUserId(int userId);


	/**
	 * 检查某个商品是否存在购物车
	 * @param cartInfo
	 * @return
	 */
	int isExist(CartInfo cartInfo);


	/**
	 * 为购物车添加信息
	 * @param cartInfo
	 */
	void addCartInfo(CartInfo cartInfo);

	/**
	 * 获得数目
	 * @param cartId
	 * @return
	 */
	int getCartInfoNum(int cartId);

	/**
	 * 获得购物车信息（分页）
	 */
	List<CartInfo> getCartInfoByPage(Map<String, Integer> map);

	/**
	 * 获得购物车信息
	 * @param cartId
	 * @return
	 */
	List<CartInfo> getCartInfoListByCartId(int cartId);

	/**
	 * 获得同一个购物车的同一个店铺的信息
	 * @param cartInfo
	 * @return
	 */
	List<CartInfo> getCartInfoByStore(CartInfo cartInfo);

	/**
	 * 获得购物车某个商品信息
	 * @param cartInfo
	 * @return
	 */
	CartInfo getCartInfoByCuisine(CartInfo cartInfo);

	/**
	 * 修改购物车某商品的数量和总价格
	 * @param cartInfo
	 */
	void modifyNumPrice(CartInfo cartInfo);
	/**
	 * 清空购物车
	 * @param cartId
	 */
	void clearCart(int cartId);

}
