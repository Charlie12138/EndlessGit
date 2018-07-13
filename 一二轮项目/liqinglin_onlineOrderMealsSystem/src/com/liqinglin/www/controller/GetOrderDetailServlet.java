package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.service.OrderService;

/**
 * 获得订单细节
 */
@WebServlet("/GetOrderDetailServlet")
public class GetOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();
		String jump = request.getParameter("jump");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		int pageSize = 5;//页面显示的最大条数
		PageBean<SingleOrder> pb = orderService.getSingleOrder(orderId, pageNum, pageSize);
		request.getSession().setAttribute("singleOrders", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("orderStatus", orderStatus);
		
		/**
		 * 判断是把数据发送到那个页面
		 */
		if(jump.equals("1")) {//店铺的订单页面
			request.getRequestDispatcher("/store/listOrderDetail.jsp").forward(request, response);
		} else if(jump.equals("2")) {//用户的订单页面
			request.getRequestDispatcher("/user/listOrderDetail.jsp").forward(request, response);
		}
	}

}
