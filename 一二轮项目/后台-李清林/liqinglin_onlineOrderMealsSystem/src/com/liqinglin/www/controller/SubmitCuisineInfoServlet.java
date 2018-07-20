package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.UploadUtil;

/**
 * 提交修改后菜肴的信息
 */
@WebServlet("/SubmitCuisineInfoServlet")
public class SubmitCuisineInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cuisine cuisine = UploadUtil.upload(request);		
		Store store =(Store)request.getSession().getAttribute("store");
		StoreService storeService = new StoreService();
		int result = storeService.submitInfo(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			response.setHeader("refresh","2;URL=/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_SUCCEESS + "</h1></center>");
		} else {
			response.setHeader("refresh","2;URL=/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_FAIL + "</h1></center>");
		}
		
	}

}
