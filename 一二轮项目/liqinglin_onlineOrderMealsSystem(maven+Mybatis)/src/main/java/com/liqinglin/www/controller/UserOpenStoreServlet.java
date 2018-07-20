package com.liqinglin.www.controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;

/**
 * 用户开店控制器
 */
@WebServlet("/UserOpenStoreServlet")
public class UserOpenStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void check(HttpServletRequest request, HttpServletResponse response) {
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

		UserService userService = new UserService();
		int result = userService.openStroe(store);

		try {
			if (result == Contants.SUCCESS_CODE) {
				request.setAttribute("message", Contants.OPENSTORE_EXAMINEING);// 提交审核成功
				request.getRequestDispatcher("/store/openStore.jsp").forward(request, response);
			} else if (result == Contants.STORENAME_EXIST_CODE) {
				request.setAttribute("message", Contants.STORENAME_EXIST);
				request.getRequestDispatcher("/store/openStore.jsp").forward(request, response);
			} else {
				request.setAttribute("message", Contants.FAIL);
				request.getRequestDispatcher("/store/openStore.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		check(request, response);
	}

}
