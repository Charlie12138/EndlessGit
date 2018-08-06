package com.liqinglin.www.controller;

import com.liqinglin.www.po.*;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.util.OrderNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PayCartOrderHandler {

	@Autowired
	CartService cartService;

	@RequestMapping(value = "/PayCartOrderServlet", method = RequestMethod.GET)
	public String payCartOrder(HttpServletRequest request) {
		/**
		 * 获得同一个购物车的商品
		 */
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		List<CartInfo> cartInfos1 = cartService.getCartInfoListByCartId(cartId);

		/**
		 * 遍历商品获得所有店铺id，放进不会存入重复数据的set集合中
		 */
		Set<Integer> set = new HashSet<Integer>();
		for(CartInfo cartInfo : cartInfos1) {
			set.add(cartInfo.getStore().getStoreId());
		}
		/**
		 * 遍历set获得, 设置同一个店铺的订单
		 */
		Double totalPrice1 = (double) 0;//所有订单的总价格
		List<TotalOrder> totalOrders = new ArrayList<TotalOrder>();
		for(Integer storeId : set) {

			Double totalPrice2 = (double) 0;//这个订单的总价格
			TotalOrder totalOrder = new TotalOrder();
			List<SingleOrder> singleOrders = new ArrayList<SingleOrder>();
			totalOrder.setOrderNum(OrderNumUtil.getOrderNum());
			Cart cart = new Cart();
			cart.setId(cartId);
			Store store = new Store();
			store.setStoreId(storeId);
			CartInfo cartInfo = new CartInfo();
			cartInfo.setCart(cart);
			cartInfo.setStore(store);
			List<CartInfo> cartInfos2 = cartService.getCartInfoByStore(cartInfo);
			/**
			 * 为单个商品的订单设置信息
			 */
			for(CartInfo cartInfo1 : cartInfos2) {
				SingleOrder singleOrder = new SingleOrder();
				Cuisine cuisine = new Cuisine();
				cuisine.setId(cartInfo1.getCuisine().getId());
				cuisine.setCuisineName(cartInfo1.getCuisine().getCuisineName());
				singleOrder.setCuisine(cuisine);
				singleOrder.setNumber(cartInfo1.getNumber());
				singleOrder.setSinglePrice(cartInfo1.getCuisine().getPrice());
				singleOrder.setStoreId(storeId);
				singleOrder.setTotalPrice(cartInfo1.getNumber() * cartInfo1.getCuisine().getPrice());
				totalPrice2 += cartInfo1.getNumber() * cartInfo1.getCuisine().getPrice();
				totalPrice1 += totalPrice2;
				singleOrders.add(singleOrder);
			}

			totalOrder.setSingleOrders(singleOrders);
			totalOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
			totalOrder.setTotalPrice(totalPrice2);
			Store store1 = new Store();
			store.setStoreId(storeId);
			totalOrder.setStore(store);
			totalOrder.setUserId(userId);
			totalOrders.add(totalOrder);
		}
		request.getSession().setAttribute("totalOrders", totalOrders);
		request.getSession().setAttribute("totalOrderAmount", set.size());
		request.getSession().setAttribute("totalPrice", totalPrice1);
		return "user/fillCartOrder";
	}
}
