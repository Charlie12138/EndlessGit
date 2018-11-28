package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.serviceImp.StoreServiceImp;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 提交修改后菜肴的信息
 */
@Controller
public class SubmitCuisineInfoHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/SubmitCuisineInfoServlet", method = RequestMethod.POST)
	public void submitCuisineInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cuisine cuisine = UploadUtil.upload(request);
		Store store =(Store)request.getSession().getAttribute("store");
		int result = storeService.submitInfo(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_SUCCEESS + "</h1></center>");
		} else {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_FAIL + "</h1></center>");
		}
	}

}
