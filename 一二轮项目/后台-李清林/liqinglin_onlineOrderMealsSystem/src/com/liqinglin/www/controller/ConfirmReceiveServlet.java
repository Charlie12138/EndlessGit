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
 * 确认收货的控制器
 */
@WebServlet("/ConfirmReceiveServlet")
public class ConfirmReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_C, orderId);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/user/userOrderIndex.jsp");
	}

}
