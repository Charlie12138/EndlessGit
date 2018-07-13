package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.StoreService;

/**
 * 加入购物车的控制器
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		StoreService storeService = new StoreService();
		CartInfo cartInfo = new CartInfo();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Cuisine cuisine = storeService.getCuisineInfo(cuisineId);
		Store store = storeService.getStoreInfo(storeId);
		Cart cart = cartService.getCart(userId);
		cartInfo.setCart(cart);
		cartInfo.setCuisine(cuisine);
		cartInfo.setStore(store);
		cartService.addCartInfo(cartInfo);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListAllCuisinesServlet?pageNum=1&jump=2");
	}

}
