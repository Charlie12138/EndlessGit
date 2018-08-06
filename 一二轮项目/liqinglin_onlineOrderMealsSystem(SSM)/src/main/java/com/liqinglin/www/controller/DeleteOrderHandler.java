package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.serviceImp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteOrderHandler {

	@Autowired
	OrderService orderService;

	@RequestMapping("/DeleteOrderServlet")
	public String deleteOrder(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = (User)request.getSession().getAttribute("user");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		orderService.deleteTotalOrder(orderId);
		redirectAttributes.addAttribute("userId", user.getId());
		redirectAttributes.addAttribute("jump", 2);
		redirectAttributes.addAttribute("pageNum", 1);
		redirectAttributes.addAttribute("orderStatus", orderStatus);
		return "redirect:ListTotalOrderServlet";
	}
}
