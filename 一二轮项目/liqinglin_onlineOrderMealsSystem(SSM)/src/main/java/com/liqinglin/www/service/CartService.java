package com.liqinglin.www.service;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.PageBean;

import java.util.List;

public interface CartService {
	void addCart(int userId);

	Cart getCartByUserId(int userId);

	void addCartInfo(CartInfo cartInfo);

	PageBean<CartInfo> getCartInfoByPage(int cartId, int pageNum, int pageSize);

	List<CartInfo> getCartInfoListByCartId(int cartId);

	List<CartInfo> getCartInfoByStore(CartInfo cartInfo);

	void clearCart(int cartId);
}
