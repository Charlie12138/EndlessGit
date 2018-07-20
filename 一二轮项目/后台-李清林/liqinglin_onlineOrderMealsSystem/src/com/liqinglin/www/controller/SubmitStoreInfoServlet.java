package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;

/**
 * 提交修改后商店的信息
 */
@WebServlet("/SubmitStoreInfoServlet")
public class SubmitStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		StoreService storeService = new StoreService();
		int result = storeService.submitStoreInfo(store);

		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.MODIFY_STORE_SUCCEESS);// 提交审核成功
			request.getRequestDispatcher("/store/modifyStoreInfo.jsp").forward(request, response);
		} else {
			request.setAttribute("message", Contants.MODIFY_STORE_FAIL);
			request.getRequestDispatcher("/store/modifyStoreInfo.jsp").forward(request, response);
		}

	}

}
