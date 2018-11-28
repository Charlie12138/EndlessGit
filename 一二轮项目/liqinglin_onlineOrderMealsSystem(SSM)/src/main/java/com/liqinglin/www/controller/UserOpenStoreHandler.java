package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
/**
 * 用户开店控制器
 */
@Controller
public class UserOpenStoreHandler {

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;


	@RequestMapping("/UserOpenStoreServlet")
	public String userOpenStore(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		String phone = request.getParameter("phone");
		String shopkeeperName = request.getParameter("shopkeeperName");
		String storeDescription = request.getParameter("storeDescription");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
		User user = new User();
		Store store = new Store();
		store.setStoreName(storeName);
		store.setPhone(phone);
		store.setAddress(address);
		store.setStoreDescription(storeDescription);
		store.setShopkeeperName(shopkeeperName);
		user.setUsername(username);
		store.setUser(user);
		store.setCreateTime(new Timestamp(System.currentTimeMillis()));

		int result = storeService.openStroe(store);
		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.OPENSTORE_EXAMINEING);// 提交审核成功
			return "store/openStore";
		} else if (result == Contants.STORENAME_EXIST_CODE) {
			request.setAttribute("message", Contants.STORENAME_EXIST);
			return "store/openStore";
		} else {
			request.setAttribute("message", Contants.FAIL);
			return "store/openStore";
		}
	}
}
