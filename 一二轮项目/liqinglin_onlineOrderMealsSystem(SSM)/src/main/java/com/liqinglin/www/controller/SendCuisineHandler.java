package com.liqinglin.www.controller;

import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.serviceImp.OrderServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 送餐控制器
 */
@Controller
public class SendCuisineHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/SendCuisineServlet", method = RequestMethod.POST)
	public String sendCuisine(HttpServletRequest request, RedirectAttributes attributes) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_B, orderId);
		attributes.addAttribute("storeId",storeId);
		attributes.addAttribute("pageNum", 1);
		attributes.addAttribute("jump", 1);
		attributes.addAttribute("orderStatus", Contants.ORDER_STATUS_A);
		return "redirect:ListTotalOrderServlet";
	}
}
