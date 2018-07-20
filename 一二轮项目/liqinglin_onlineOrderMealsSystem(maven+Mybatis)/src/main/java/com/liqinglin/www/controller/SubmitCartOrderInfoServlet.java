package com.liqinglin.www.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;

/**
 * 提交购物车订单
 */
@WebServlet("/SubmitCartOrderInfoServlet")
public class SubmitCartOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService();
		@SuppressWarnings("unchecked")
		List<TotalOrder> totalOrders = (ArrayList<TotalOrder>) request.getSession().getAttribute("totalOrders");
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");

		/**
		 * 校验是否为空
		 */
		if (orderService.check(address, message, phone, receiver)) {
			request.getSession().setAttribute("msg", "必填信息不能为空");
			request.getRequestDispatcher("/user/fillCartOrder.jsp").forward(request, response);
			return;
		}

		for (TotalOrder totalOrder : totalOrders) {
			totalOrder.setAddress(address);
			totalOrder.setMessage(message);
			totalOrder.setPhone(phone);
			totalOrder.setReceiver(receiver);
			/**
			 * 添加总订单
			 */
			orderService.addTotalOrder(totalOrder);

			/**
			 * 获得总订单
			 */
			TotalOrder totalOrder2 = orderService.getTotalOrder(totalOrder.getOrderNum());

			List<SingleOrder> singleOrders = totalOrder.getSingleOrders();
			for (SingleOrder singleOrder : singleOrders) {
				/**
				 * 添加单条订单
				 */
				singleOrder.setOrderId(totalOrder2.getId());
				orderService.addSingleOrder(singleOrder);
				/**
				 * 修改销量
				 */
				StoreService sv = new StoreService();
				sv.modifySellCount(singleOrder.getCuisine().getId(), singleOrder.getNumber());
			}
		}
		/**
		 * 清空购物车
		 */
		User user = (User) request.getSession().getAttribute("user");
		CartService cartService = new CartService();
		int userId = user.getId();
		int cartId = cartService.getCart(userId).getId();
		cartService.clearCart(cartId);
		response.sendRedirect("user/paySuccess.jsp");

	}

}
