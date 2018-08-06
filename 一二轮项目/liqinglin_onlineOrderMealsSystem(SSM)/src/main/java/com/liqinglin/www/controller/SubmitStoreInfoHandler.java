package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubmitStoreInfoHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping("/modifyStoreInfo")
	public String modifyStoreInfo() {
		return "store/modifyStoreInfo";
	}

	@RequestMapping(value = "/SubmitStoreInfoServlet", method = RequestMethod.POST)
	public String submitStoreInfo(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		String phone = request.getParameter("phone");
		String shopkeeperName = request.getParameter("shopkeeperName");
		String storeDescription = request.getParameter("storeDescription");
		String address = request.getParameter("address");
		int storeId = Integer.parseInt(request.getParameter("storeId"));

		Store store = new Store();
		store.setStoreName(storeName);
		store.setPhone(phone);
		store.setAddress(address);
		store.setStoreDescription(storeDescription);
		store.setShopkeeperName(shopkeeperName);
		store.setStoreId(storeId);

		int result = storeService.submitStoreInfo(store);

		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.MODIFY_STORE_SUCCEESS);// 提交审核成功
			return "store/modifyStoreInfo";
		} else {
			request.setAttribute("message", Contants.MODIFY_STORE_FAIL);
			return "store/modifyStoreInfo";
		}
	}


}
