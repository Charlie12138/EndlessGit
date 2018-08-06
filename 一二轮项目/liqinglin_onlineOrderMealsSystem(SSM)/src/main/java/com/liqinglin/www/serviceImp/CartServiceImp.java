package com.liqinglin.www.serviceImp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.dao.CartMapper;
import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImp implements com.liqinglin.www.service.CartService {

	@Autowired
	CartMapper cartMapper;
	/**
	 * 为用户添加购物车
	 */
	@Override
	public void addCart(int userId) {
		if(cartMapper.isHasCart(userId) == 0) {
			cartMapper.addCart(userId);
		}
	}
	
	/**
	 * 获得购物车
	 */
	@Override
	public Cart getCartByUserId(int userId) {
		return cartMapper.getCartByUserId(userId);
	}
	
	/**
	 * 为购物车添加信息
	 */
	@Override
	public void addCartInfo(CartInfo cartInfo) {
		if(cartMapper.isExist(cartInfo) != 0) {
			cartInfo = cartMapper.getCartInfoByCuisine(cartInfo);
			cartInfo.setNumber(cartInfo.getNumber() + 1);
			cartInfo.setTotalPrice( cartInfo.getTotalPrice() + cartInfo.getCuisine().getPrice());
			cartMapper.modifyNumPrice(cartInfo);
			return;
		}
		cartInfo.setNumber(1);
		cartInfo.setTotalPrice(cartInfo.getCuisine().getPrice());
		cartMapper.addCartInfo(cartInfo);
	}
	
	/**
	 * 获得购物车信息
	 */
	@Override
	public PageBean<CartInfo> getCartInfoByPage(int cartId, int pageNum, int pageSize) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int totalRecord = cartMapper.getCartInfoNum(cartId);
		PageBean<CartInfo> pb = new PageBean<CartInfo>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();
		map.put("cartId", cartId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		pb.setList(cartMapper.getCartInfoByPage(map));
		return pb;
	}
	
	/**
	 * 获得同一个购物车信息
	 */
	@Override
	public List<CartInfo> getCartInfoListByCartId(int cartId) {
		return cartMapper.getCartInfoListByCartId(cartId);
	}
	
	/**
	 * 获得同一个购物车同一个店铺的信息
	 */

	@Override
	public List<CartInfo> getCartInfoByStore(CartInfo cartInfo) {
		return cartMapper.getCartInfoByStore(cartInfo);
	}

	
	/**
	 * 清空购物车
	 */
	@Override
	public void clearCart(int cartId) {
		cartMapper.clearCart(cartId);
	}
	
	
	
}

