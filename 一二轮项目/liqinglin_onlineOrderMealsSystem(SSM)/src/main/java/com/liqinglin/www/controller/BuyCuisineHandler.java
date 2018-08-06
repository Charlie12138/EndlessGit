package com.liqinglin.www.controller;

import com.liqinglin.www.po.*;
import com.liqinglin.www.util.OrderNumUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class BuyCuisineHandler {

	@RequestMapping("buyNow")
	public String buyNow() {
		return "user/buyNow";
	}

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
}
