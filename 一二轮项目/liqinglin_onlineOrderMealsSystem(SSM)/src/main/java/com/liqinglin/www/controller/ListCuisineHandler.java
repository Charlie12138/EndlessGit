package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ListCuisineHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/ListCuisine", method = RequestMethod.GET)
	public String listCuisine(HttpServletRequest request, @RequestParam("storeId") Integer storeId, @RequestParam("pageNum") Integer pageNum, @RequestParam("jump") Integer jump) {
		Store store = storeService.getStoreInfo(storeId);
		request.getSession().setAttribute("store", store);
		request.getSession().setAttribute("status0", Contants.EXAMINEING);
		request.getSession().setAttribute("status1", Contants.PASS_EXAMINE);
		request.getSession().setAttribute("status2", Contants.REJECT_EXAMINE);
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(storeId, pageNum, pageSize);
		request.getSession().setAttribute("cuisines", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		int status = pb.getList().get(0).getStatus();
		request.setAttribute("cuisineStatus", status);
		if(jump == 1) {
			return "store/storeIndex";
		} else if(jump == 2) {
			return "store/deleteCuisine";
		} else if(jump == 3) {
			return "store/modifyCuisineInfoIndex";
		}
		return null;
	}



}
