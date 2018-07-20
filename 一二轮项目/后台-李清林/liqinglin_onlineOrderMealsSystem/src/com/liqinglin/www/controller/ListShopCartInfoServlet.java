package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cart;
import com.liqinglin.www.po.CartInfo;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.service.CartService;

/**
 * 列出用户的购物车内容的控制器
 */
@WebServlet("/ListShopCartInfoServlet")
public class ListShopCartInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Cart cart = cartService.getCart(userId);
		int pageSize = 5;//每页显示5条数据
		PageBean<CartInfo> pb = cartService.getCartInfos(cart.getId(), pageNum, pageSize);
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("cartInfos", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.getRequestDispatcher("/user/myShopCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
