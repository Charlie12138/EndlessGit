package com.liqinglin.www.controller;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubmitOrderInfoHandler {

	@Autowired
	OrderService orderService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/SubmitOrderInfoServlet")
	public String submitOrderInfo(HttpServletRequest request) {
		SingleOrder singleOrder = (SingleOrder)request.getSession().getAttribute(("singleOrder"));
		TotalOrder totalOrder = (TotalOrder)request.getSession().getAttribute("totalOrder");

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
			return "user/fillOrder";
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
		storeService.modifySellCount(singleOrder.getCuisine().getId(), singleOrder.getNumber());
		return "user/paySuccess";
	}



}
