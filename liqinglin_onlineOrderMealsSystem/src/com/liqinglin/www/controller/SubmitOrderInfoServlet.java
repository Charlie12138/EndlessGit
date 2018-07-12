package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;

/**
 * 提交订单
 */
@WebServlet("/SubmitOrderInfoServlet")
public class SubmitOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SingleOrder singleOrder = (SingleOrder)request.getSession().getAttribute(("singleOrder"));
		TotalOrder totalOrder = (TotalOrder)request.getSession().getAttribute("totalOrder");
		OrderService orderService = new OrderService();
		
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		
		totalOrder.setAddress(address);
		totalOrder.setMessage(message);
		totalOrder.setPhone(phone);
		totalOrder.setReceiver(receiver);
		
		/**
		 * 校验是否为空
		 */
		if(orderService.check(totalOrder)) {
			request.getSession().setAttribute("msg", "必填信息不能为空");
			request.getRequestDispatcher("/user/fillOrder.jsp").forward(request, response);
			return;
		}
		
		/**
		 * 添加总订单
		 */
		orderService.addTotalOrder(totalOrder);
		
		/**
		 * 获得总订单
		 */
		totalOrder = orderService.getTotalOrder(totalOrder.getOrderNum());
		/**
		 * 添加单条订单
		 */
		singleOrder.setOrderId(totalOrder.getId());
		orderService.addSingleOrder(singleOrder);
		/**
		 * 修改销量
		 */
		StoreService sv = new StoreService();
		sv.modifySellCount(singleOrder.getCuisineId(), singleOrder.getNumber());
		
		response.sendRedirect("user/paySuccess.jsp");
		
	}

}
