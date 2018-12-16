package com.liqinglin.www.controller;

import com.liqinglin.www.po.*;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.OrderNumUtil;
import com.liqinglin.www.po.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	StoreService storeService;

	/**
	 * 添加到购物车
	 */
	@RequestMapping("/AddToCartServlet")
	public String AddToCart(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//封装购物车信息
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


	/**
	 * 清空购物车
	 */
	@RequestMapping(value = "/ClearCartServlet", method = RequestMethod.GET)
	public String clearCart(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/*
		  获得购物车
		 */
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		/*
		  清空
		 */
		cartService.clearCart(cartId);
		redirectAttributes.addAttribute("pageNum", 1);
		return "redirect:ListShopCartInfoServlet";
	}

	/**
	 * 罗列购物车信息
	 */
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

	/**
	 * 支付购物车
	 */
	@RequestMapping(value = "/PayCartOrderServlet", method = RequestMethod.GET)
	public String payCartOrder(HttpServletRequest request) {
		/*
		  获得同一个购物车的商品
		 */
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		List<CartInfo> cartInfos1 = cartService.getCartInfoListByCartId(cartId);

		/*
		  遍历商品获得所有店铺id，放进不会存入重复数据的set集合中
		 */
		Set<Integer> set = new HashSet<Integer>();
		for(CartInfo cartInfo : cartInfos1) {
			set.add(cartInfo.getStore().getStoreId());
		}
		/*
		  遍历set获得, 设置同一个店铺的订单
		 */
		Double totalPrice1 = (double) 0;//所有订单的总价格
		List<TotalOrder> totalOrders = new ArrayList<TotalOrder>();
		for(Integer storeId : set) {

			Double totalPrice2 = (double) 0;//这个订单的总价格
			TotalOrder totalOrder = new TotalOrder();
			List<SingleOrder> singleOrders = new ArrayList<SingleOrder>();
			totalOrder.setOrderNum(OrderNumUtil.getOrderNum());
			Cart cart = new Cart();
			cart.setId(cartId);
			Store store = new Store();
			store.setStoreId(storeId);
			CartInfo cartInfo = new CartInfo();
			cartInfo.setCart(cart);
			cartInfo.setStore(store);
			List<CartInfo> cartInfos2 = cartService.getCartInfoByStore(cartInfo);
			/*
			  为单个商品的订单设置信息
			 */
			for(CartInfo cartInfo1 : cartInfos2) {
				SingleOrder singleOrder = new SingleOrder();
				Cuisine cuisine = new Cuisine();
				cuisine.setId(cartInfo1.getCuisine().getId());
				cuisine.setCuisineName(cartInfo1.getCuisine().getCuisineName());
				singleOrder.setCuisine(cuisine);
				singleOrder.setNumber(cartInfo1.getNumber());
				singleOrder.setSinglePrice(cartInfo1.getCuisine().getPrice());
				singleOrder.setStoreId(storeId);
				singleOrder.setTotalPrice(cartInfo1.getNumber() * cartInfo1.getCuisine().getPrice());
				totalPrice2 += cartInfo1.getNumber() * cartInfo1.getCuisine().getPrice();
				totalPrice1 += totalPrice2;
				singleOrders.add(singleOrder);
			}

			totalOrder.setSingleOrders(singleOrders);
			totalOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
			totalOrder.setTotalPrice(totalPrice2);
			store.setStoreId(storeId);
			totalOrder.setStore(store);
			totalOrder.setUserId(userId);
			totalOrders.add(totalOrder);
		}
		request.getSession().setAttribute("totalOrders", totalOrders);
		request.getSession().setAttribute("totalOrderAmount", set.size());
		request.getSession().setAttribute("totalPrice", totalPrice1);
		return "user/fillCartOrder";
	}

	/**
	 * 提交购物车订单信息
	 */
	@RequestMapping(value = "/SubmitCartOrderInfoServlet", method = RequestMethod.POST)
	public String submitCartOrderInfo(HttpServletRequest request) {
		List<TotalOrder> totalOrders = (ArrayList<TotalOrder>) request.getSession().getAttribute("totalOrders");
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");

		/*
		  校验是否为空
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
			/*
			  添加总订单
			 */
			orderService.addTotalOrder(totalOrder);

			/*
			  获得总订单
			 */

			TotalOrder totalOrder2 = orderService.getTotalOrder(totalOrder.getOrderNum());

			List<SingleOrder> singleOrders = totalOrder.getSingleOrders();
			for (SingleOrder singleOrder : singleOrders) {
				/*
				  添加单条订单
				 */
				singleOrder.setOrderId(totalOrder2.getId());
				orderService.addSingleOrder(singleOrder);
				/*
				  修改销量
				 */
				storeService.modifySellCount(singleOrder.getCuisine().getId(), singleOrder.getNumber());
			}
		}
		/*
		  清空购物车
		 */
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		int cartId = cartService.getCartByUserId(userId).getId();
		cartService.clearCart(cartId);
		return "user/paySuccess";
	}
}
