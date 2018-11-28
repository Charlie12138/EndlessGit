package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.serviceImp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ListCartInfoHandler {

	@Autowired
	CartService cartService;

	@RequestMapping(value = "/ListShopCartInfoServlet", method = RequestMethod.GET)
	public String listCartInfo(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum) {
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		Cart cart = cartService.getCartByUserId(userId);
		int pageSize = 5;//每页显示5条数据
		PageBean<CartInfo> pb = cartService.getCartInfoByPage(cart.getId(), pageNum, pageSize);
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("cartInfos", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		return "user/myShopCart";
	}
}
