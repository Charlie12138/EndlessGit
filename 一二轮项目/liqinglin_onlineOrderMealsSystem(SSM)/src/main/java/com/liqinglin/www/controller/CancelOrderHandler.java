package com.liqinglin.www.controller;

import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.serviceImp.OrderServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CancelOrderHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping("/CancelOrderServlet")
	public String cancelOrderHandler(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String jump = request.getParameter("jump");
		int status = Integer.parseInt(request.getParameter("orderStatus"));
		int id = Integer.parseInt(request.getParameter("id"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_D, orderId);
		redirectAttributes.addAttribute("pageNum", 1);
		redirectAttributes.addAttribute("orderStatus", status);
		if(jump.equals("1")) {//往店铺的页面跳转
			redirectAttributes.addAttribute("jump", jump);
			redirectAttributes.addAttribute("storeId", id);
			return "redirect:ListTotalOrderServlet";
		} else if(jump.equals("2")) {//往用户的界面跳转
			redirectAttributes.addAttribute("jump", jump);
			redirectAttributes.addAttribute("userId", id);
			return "redirect:ListTotalOrderServlet";
		}
		return null;
	}


}
