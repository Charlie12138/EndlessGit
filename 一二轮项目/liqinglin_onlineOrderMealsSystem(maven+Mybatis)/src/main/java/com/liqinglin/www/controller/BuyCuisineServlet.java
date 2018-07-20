package com.liqinglin.www.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.util.OrderNumUtil;

/**
 * 下单控制器
 */
@WebServlet("/BuyCuisineServlet")
public class BuyCuisineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SingleOrder singleOrder = new SingleOrder();
		TotalOrder totalOrder = new TotalOrder();
		Store store = new Store();
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		String cuisineName = request.getParameter("cuisineName");
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Double singlePrice = Double.parseDouble(request.getParameter("price"));
		int number = Integer.parseInt(request.getParameter("number"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		Double totalPrice = number * singlePrice;//计算单品总价格
		String orderNum = OrderNumUtil.getOrderNum();//获得订单编号
		Cuisine cuisine = new Cuisine();
		cuisine.setId(cuisineId);
		cuisine.setCuisineName(cuisineName);
		singleOrder.setCuisine(cuisine);
		singleOrder.setNumber(number);
		singleOrder.setTotalPrice(totalPrice);
		singleOrder.setStoreId(storeId);
		
		totalOrder.setOrderNum(orderNum);
		totalOrder.setTotalPrice(totalPrice);
		store.setStoreId(storeId);
		totalOrder.setStore(store);
		totalOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
		totalOrder.setUserId(userId);
		request.getSession().setAttribute("singlePrice", singlePrice);
		request.getSession().setAttribute("singleOrder", singleOrder);
		request.getSession().setAttribute("totalOrder", totalOrder);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/user/fillOrder.jsp");	
	}

}
