package com.liqinglin.www.controller;

import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ComfirmReceiveHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping("/ConfirmReceiveServlet")
	public String comfirmReceive(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_C, orderId);
		return "user/userOrderIndex";
	}
}
