package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AddCuisineHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping("/addCuisine")
	public String add() {
		return "store/addCuisine";
	}

	@RequestMapping("/AddCuisinesServlet")
	public String addCuisine(HttpServletRequest request, HttpServletResponse response) {
		Store store = (Store)request.getSession().getAttribute("store");
		Cuisine cuisine = UploadUtil.upload(request);
		cuisine.setStore(store);
		if(storeService.imageFormatIsRight(cuisine.getPicturePath()) == Contants.IMAGE_FORMAT_IS_ERROR_CODE) {
			request.setAttribute("message", Contants.IMAGE_FORMAT_IS_ERROR);
			return "store/addGoods";
		}
		int result = storeService.addCuisine(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=1");
			try {
				response.getWriter().println("<center><h1>" + Contants.ADDCUISINE_SUCCEESS + "</h1></center>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=1");
			try {
				response.getWriter().println("<center><h1>" + Contants.ADDCUISINE_FAIL + "</h1></center>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
