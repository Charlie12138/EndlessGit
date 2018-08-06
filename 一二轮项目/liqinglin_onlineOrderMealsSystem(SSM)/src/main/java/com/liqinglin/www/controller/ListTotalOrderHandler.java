package com.liqinglin.www.controller;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.serviceImp.OrderServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ListTotalOrderHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping("/handlerOrder")
	public String handleOrder() {
		return "store/handleOrder";
	}

	@RequestMapping("/userOrderIndex")
	public String orderIndex() {
		return "user/userOrderIndex";
	}

	@RequestMapping(value = "/ListTotalOrderServlet", method = RequestMethod.GET)
	public String listTotalOrderHandler(HttpServletRequest request, @RequestParam("jump") Integer jump,
		@RequestParam("pageNum") Integer pageNum, @RequestParam("orderStatus") Integer orderStatus) {
		request.setAttribute("orderStatus", orderStatus);
		int pageSize = 5;//页面显示的最大条数
		/**
		 * 判断是把数据发送到那个页面
		 */
		if(jump == 1) {//店铺的订单页面
			int storeId = Integer.parseInt(request.getParameter("storeId"));
			PageBean<TotalOrder> pb = orderService.getOrder(orderStatus, storeId, pageNum, pageSize, Contants.STORE_ORDER);
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "store/listTotalOrder";
		} else if(jump == 2) {//用户的订单页面
			int userId = Integer.parseInt(request.getParameter("userId"));
			PageBean<TotalOrder> pb = orderService.getOrder(orderStatus, userId, pageNum, pageSize, Contants.USER_ORDER);
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "user/listTotalOrder";
		}
		return null;
	}

}
