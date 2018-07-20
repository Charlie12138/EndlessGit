package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.util.Contants;

/**
 * 送餐控制器
 */
@WebServlet("/SendCuisineServlet")
public class SendCuisineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_B, orderId);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=" + storeId + "&pageNum=1&jump=1");
	}

}
