package com.liqinglin.www.controller;

import com.liqinglin.www.po.*;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
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

@Controller
public class OrderController {

	@Autowired
	StoreService storeService;

	@Autowired
	OrderService orderService;
	/**
	 * 立刻购买
	 * @return 结账
	 */
	@RequestMapping("buyNow")
	public String buyNow() {
		return "user/buyNow";
	}

	/**
	 * 生成订单
	 * @param request 请求
	 */
	@RequestMapping("/BuyCuisineServlet")
	public String buyCuisine(HttpServletRequest request) {
		SingleOrder singleOrder = new SingleOrder();
		TotalOrder totalOrder = new TotalOrder();
		Store store = new Store();
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		String cuisineName = request.getParameter("cuisineName");
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		Double singlePrice = Double.parseDouble(request.getParameter("price"));
		int number = Integer.parseInt(request.getParameter("number"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		Double totalPrice = number * singlePrice;//计算单品总价格
		String orderNum = OrderNumUtil.getOrderNum();//获得订单编号
		Cuisine cuisine = new Cuisine();
		cuisine.setId(cuisineId);
		cuisine.setCuisineName(cuisineName);
		singleOrder.setCuisine(cuisine);
		singleOrder.setNumber(number);
		singleOrder.setTotalPrice(totalPrice);
		singleOrder.setStoreId(storeId);

		totalOrder.setOrderNum(orderNum);
		totalOrder.setTotalPrice(totalPrice);
		store.setStoreId(storeId);
		totalOrder.setStore(store);
		totalOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
		totalOrder.setUserId(userId);
		request.getSession().setAttribute("singlePrice", singlePrice);
		request.getSession().setAttribute("singleOrder", singleOrder);
		request.getSession().setAttribute("totalOrder", totalOrder);
		return "user/fillOrder";
	}


	/**
	 * 取消订单
	 */
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

	/**
	 * 确认送达
	 */
	@RequestMapping("/ConfirmReceiveServlet")
	public String comfirmReceive(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_C, orderId);
		return "user/userOrderIndex";
	}

	/**
	 * 删除订单
	 */
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

	/**
	 * 订单详情
	 */
	@RequestMapping(value = "/GetOrderDetailServlet", method = RequestMethod.POST)
	public String getOrderDetail(HttpServletRequest request) {
		String jump = request.getParameter("jump");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		int pageSize = 5;//页面显示的最大条数
		PageBean<SingleOrder> pb = orderService.getSingleOrder(orderId, pageNum, pageSize);
		if (pb.getList().size() == 0) {
			if(jump.equals("1")) {//店铺的订单页面
				return "store/listOrderDetail";
			} else if(jump.equals("2")) {//用户的订单页面
				return "user/listOrderDetail";
			}
		}
		request.getSession().setAttribute("singleOrders", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("orderStatus", orderStatus);

		/*
		  判断是把数据发送到那个页面
		 */
		if(jump.equals("1")) {//店铺的订单页面
			return "store/listOrderDetail";
		} else if(jump.equals("2")) {//用户的订单页面
			return "user/listOrderDetail";
		}
		return null;
	}

	/**
	 * 罗列已发送订单
	 */
	@RequestMapping(value = "/ListSendedOrderServlet", method = RequestMethod.GET)
	public String listSendOrder(HttpServletRequest request) {
		String jump = request.getParameter("jump");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = 5;//页面显示的最大条数
		/*
		  判断是把数据发送到哪个页面
		 */
		if(jump.equals("1")) {//店铺的订单页面
			int storeId = Integer.parseInt(request.getParameter("storeId"));
			PageBean<TotalOrder> pb = orderService.getOrder(Contants.ORDER_STATUS_B, storeId, pageNum, pageSize, Contants.STORE_ORDER);
			if (pb.getList().size() == 0) {
				return "store/listTotalOrder";
			}
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "store/listTotalOrder";
		} else if(jump.equals("2")) {//用户的订单页面
			int userId = Integer.parseInt(request.getParameter("userId"));
			PageBean<TotalOrder> pb = orderService.getOrder(Contants.ORDER_STATUS_B, userId, pageNum, pageSize, Contants.USER_ORDER);
			if (pb.getList().size() == 0) {
				return "user/listTotalOrder";
			}
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "user/listTotalOrder";
		}
		return null;
	}

	/**
	 * 处理订单
	 */
	@RequestMapping("/handlerOrder")
	public String handleOrder() {
		return "store/handleOrder";
	}

	/**
	 * 订单主页
	 */
	@RequestMapping("/userOrderIndex")
	public String orderIndex() {
		return "user/userOrderIndex";
	}

	/**
	 * 罗列所有订单
	 * @param request 请求
	 * @param jump 跳转方向
	 * @param pageNum 页数
	 * @param orderStatus 订单状态
	 */
	@RequestMapping(value = "/ListTotalOrderServlet", method = RequestMethod.GET)
	public String listTotalOrderHandler(HttpServletRequest request,
										@RequestParam("jump") Integer jump,
										@RequestParam("pageNum") Integer pageNum,
										@RequestParam("orderStatus") Integer orderStatus) {
		request.setAttribute("orderStatus", orderStatus);
		int pageSize = 5;//页面显示的最大条数

		if(jump == 1) {//店铺的订单页面
			int storeId = Integer.parseInt(request.getParameter("storeId"));
			PageBean<TotalOrder> pb = orderService.getOrder(orderStatus, storeId, pageNum, pageSize, Contants.STORE_ORDER);
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "store/listTotalOrder";
		} else if(jump == 2) {//用户的订单页面
			int userId = Integer.parseInt(request.getParameter("userId"));
			PageBean<TotalOrder> pb = orderService.getOrder(orderStatus, userId, pageNum, pageSize, Contants.USER_ORDER);
			if (pb.getList().size() == 0) {
				return "user/listTotalOrder";
			}
			request.getSession().setAttribute("totalOrders", pb.getList());
			request.setAttribute("pageNum", pb.getPageNum());
			request.setAttribute("totalPage", pb.getTotalPage());
			request.setAttribute("totalRecord", pb.getTotalRecord());
			return "user/listTotalOrder";
		}
		return null;
	}


	@RequestMapping("/SubmitOrderInfoServlet")
	public String submitOrderInfo(HttpServletRequest request) {
		SingleOrder singleOrder = (SingleOrder)request.getSession().getAttribute(("singleOrder"));
		TotalOrder totalOrder = (TotalOrder)request.getSession().getAttribute("totalOrder");

		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");

		totalOrder.setAddress(address);
		totalOrder.setMessage(message);
		totalOrder.setPhone(phone);
		totalOrder.setReceiver(receiver);

		/*
		  校验是否为空
		 */
		if(orderService.check(totalOrder)) {
			request.getSession().setAttribute("msg", "必填信息不能为空");
			return "user/fillOrder";
		}

		/*
		  添加总订单
		 */
		orderService.addTotalOrder(totalOrder);

		/*
		  获得总订单
		 */
		totalOrder = orderService.getTotalOrder(totalOrder.getOrderNum());
		/*
		  添加单条订单
		 */
		singleOrder.setOrderId(totalOrder.getId());
		orderService.addSingleOrder(singleOrder);
		/*
		  修改销量
		 */
		storeService.modifySellCount(singleOrder.getCuisine().getId(), singleOrder.getNumber());
		return "user/paySuccess";
	}

}
