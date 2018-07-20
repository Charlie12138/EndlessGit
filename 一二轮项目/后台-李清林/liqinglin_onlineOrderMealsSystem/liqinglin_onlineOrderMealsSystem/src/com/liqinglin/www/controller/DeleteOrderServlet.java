package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.OrderService;

/**
 * 删除订单控制器
 */
@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService();
		User user = (User)request.getSession().getAttribute("user");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		orderService.deleteTotalOrder(orderId);
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=" + user.getId() + "&jump=2&pageNum=1&orderStatus=" + orderStatus);

	}

}
