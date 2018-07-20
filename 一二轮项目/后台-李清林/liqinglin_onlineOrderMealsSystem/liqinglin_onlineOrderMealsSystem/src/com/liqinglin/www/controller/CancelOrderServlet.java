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
 * 取消订单的控制器
 */
@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String jump = request.getParameter("jump");
		int status = Integer.parseInt(request.getParameter("orderStatus"));
		int id = Integer.parseInt(request.getParameter("id"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_D, orderId);
		if(jump.equals("1")) {//往店铺的页面跳转
			response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?jump="+jump+"&orderStatus="+status+"&pageNum=1&storeId="+id);
		} else if(jump.equals("2")) {//往用户的界面跳转
			response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?jump="+jump+"&orderStatus="+status+"&pageNum=1&userId="+id);
		}
	}

}
