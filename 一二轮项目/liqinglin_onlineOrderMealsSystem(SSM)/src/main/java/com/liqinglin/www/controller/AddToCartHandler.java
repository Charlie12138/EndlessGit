package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddToCartHandler {

	@Autowired
	CartService cartService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/AddToCartServlet")
	public String AddToCart(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		CartInfo cartInfo = new CartInfo();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Cuisine cuisine = storeService.getCuisineInfo(cuisineId);
		Store store = storeService.getStoreInfo(storeId);
		Cart cart = cartService.getCartByUserId(userId);
		cartInfo.setCart(cart);
		cartInfo.setCuisine(cuisine);
		cartInfo.setStore(store);
		cartService.addCartInfo(cartInfo);
		redirectAttributes.addAttribute("pageNum", pageNum);
		redirectAttributes.addAttribute("jump", 2);
		return "redirect:ListAllCuisine";
	}
}
