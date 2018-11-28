package com.liqinglin.www.controller;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.serviceImp.StoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubmitCartOrderInfoHandler {

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/SubmitCartOrderInfoServlet", method = RequestMethod.POST)
	public String submitCartOrderInfo(HttpServletRequest request) {
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
			return "user/fillCartOrder";
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
				storeService.modifySellCount(singleOrder.getCuisine().getId(), singleOrder.getNumber());
			}
		}
		/**
		 * 清空购物车
		 */
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		cartService.clearCart(cartId);
		return "user/paySuccess";
	}

}
