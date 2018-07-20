package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;

/**
 * 清空购物车的控制器
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 获得购物车
		 */
		User user = (User)request.getSession().getAttribute("user");
		CartService cartService = new CartService();
		int userId = user.getId();
		int cartId = cartService.getCart(userId).getId();
		/**
		 * 清空
		 */
		cartService.clearCart(cartId);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?pageNum=1");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
