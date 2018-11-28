package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.serviceImp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClearCartHandler {

	@Autowired
	CartService cartService;

	@RequestMapping(value = "/ClearCartServlet", method = RequestMethod.GET)
	public String clearCart(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/**
		 * 获得购物车
		 */
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		/**
		 * 清空
		 */
		cartService.clearCart(cartId);
		redirectAttributes.addAttribute("pageNum", 1);
		return "redirect:ListShopCartInfoServlet";
	}
}
