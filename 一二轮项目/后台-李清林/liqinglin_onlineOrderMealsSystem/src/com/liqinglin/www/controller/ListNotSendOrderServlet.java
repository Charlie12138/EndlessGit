package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.util.Contants;

/**
 * 列出未发货订单控制器
 */
@WebServlet("/ListNotSendOrderServlet")
public class ListNotSendOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();		
		String jump = request.getParameter("jump");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = 5;//页面显示的最大条数
		/**
		 * 判断是把数据发送到那个页面
		 */
		if(jump.equals("1")) {//店铺的订单页面
			int storeId = Integer.parseInt(request.getParameter("storeId"));
			PageBean<TotalOrder> pb = orderService.getOrder(Contants.ORDER_STATUS_A, storeId, pageNum, pageSize, Contants.STORE_ORDER);
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());		
			request.getRequestDispatcher("/store/listTotalOrder.jsp").forward(request, response);
		} else if(jump.equals("2")) {//用户的订单页面
			int userId = Integer.parseInt(request.getParameter("userId"));
			PageBean<TotalOrder> pb = orderService.getOrder(Contants.ORDER_STATUS_A, userId, pageNum, pageSize, Contants.USER_ORDER);
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());		
			request.getRequestDispatcher("/user/listTotalOrder.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
