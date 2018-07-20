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
 * 上架美食的控制器
 */
@WebServlet("/AddCuisinesServlet")
public class AddCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreService storeService = new StoreService();
		Store store = (Store)request.getSession().getAttribute("store");
		Cuisine cuisine = UploadUtil.upload(request);
		cuisine.setStore(store);
		if(storeService.imageFormatIsRight(cuisine.getPicturePath()) == Contants.IMAGE_FORMAT_IS_ERROR_CODE) {
			request.setAttribute("message", Contants.IMAGE_FORMAT_IS_ERROR);			
			request.getRequestDispatcher("/store/addGoods.jsp").forward(request,response);
			return;
		} 
		int result = storeService.addCuisine(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			response.setHeader("refresh","2;URL=/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId="+store.getStoreId()+"&pageNum=1&jump=1");
			response.getWriter().println("<center><h1>" + Contants.ADDCUISINE_SUCCEESS + "</h1></center>");
		} else {
			response.setHeader("refresh","2;URL=/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId="+store.getStoreId()+"&pageNum=1&jump=1");
			response.getWriter().println("<center><h1>" + Contants.ADDCUISINE_FAIL + "</h1></center>");
		}
	}

}
