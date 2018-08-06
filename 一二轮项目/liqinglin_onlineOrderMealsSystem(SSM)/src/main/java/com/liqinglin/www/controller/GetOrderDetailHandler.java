package com.liqinglin.www.controller;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.serviceImp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GetOrderDetailHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/GetOrderDetailServlet", method = RequestMethod.POST)
	public String getOrderDetail(HttpServletRequest request) {
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
			return "store/listOrderDetail";
		} else if(jump.equals("2")) {//用户的订单页面
			return "user/listOrderDetail";
		}
		return null;
	}
}
