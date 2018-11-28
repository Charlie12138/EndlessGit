package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ListAllCuisineHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping("/toAdminIndex")
	public String toAdminIndex() {
		return "admin/adminIndex";
	}

	@RequestMapping(value = "/ListAllCuisine")
	public String listAllCuisine(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum, @RequestParam("jump") Integer jump) {
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(pageNum, pageSize);
		request.getSession().setAttribute("cuisines", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		int status = pb.getList().get(0).getStatus();
		request.setAttribute("cuisineStatus", status);
		if(jump == 1) {
			return "public/index";
		} else if(jump == 2){
			return "user/userIndex";
		}
		return null;
	}


}
