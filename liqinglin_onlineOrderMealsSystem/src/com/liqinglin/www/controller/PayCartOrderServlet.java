package com.liqinglin.www.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.util.OrderNumUtil;

/**
 * 购物车下单控制器
 */
@WebServlet("/PayCartOrderServlet")
public class PayCartOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * 获得同一个购物车的商品
		 */
		User user = (User)request.getSession().getAttribute("user");
		CartService cartService = new CartService();
		int userId = user.getId();
		int cartId = cartService.getCart(userId).getId();
		List<CartInfo> cartInfos1 = cartService.getCartInfos(cartId);

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
			List<CartInfo> cartInfos2 = cartService.getCartInfos(cartId, storeId);
			/**
			 * 为单个商品的订单设置信息
			 */
			for(CartInfo cartInfo : cartInfos2) {
				SingleOrder singleOrder = new SingleOrder();
				singleOrder.setCuisineId(cartInfo.getCuisine().getId());
				singleOrder.setCuisineName(cartInfo.getCuisine().getCuisineName());
				singleOrder.setNumber(cartInfo.getNumber());
				singleOrder.setSinglePrice(cartInfo.getCuisine().getPrice());
				singleOrder.setStoreId(storeId);
				singleOrder.setTotalPrice(cartInfo.getNumber() * cartInfo.getCuisine().getPrice());
				totalPrice2 += cartInfo.getNumber() * cartInfo.getCuisine().getPrice();
				totalPrice1 += totalPrice2;
				singleOrders.add(singleOrder);
			}
			
			totalOrder.setSingleOrders(singleOrders);
			totalOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
			totalOrder.setTotalPrice(totalPrice2);
			totalOrder.setStoreId(storeId);
			totalOrder.setUserId(userId);
			totalOrders.add(totalOrder);
		}
		request.getSession().setAttribute("totalOrders", totalOrders);
		request.getSession().setAttribute("totalOrderAmount", set.size());
		request.getSession().setAttribute("totalPrice", totalPrice1);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/user/fillCartOrder.jsp");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
