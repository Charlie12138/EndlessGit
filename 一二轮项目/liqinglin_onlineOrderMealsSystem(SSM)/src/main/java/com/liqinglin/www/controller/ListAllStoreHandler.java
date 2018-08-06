package com.liqinglin.www.controller;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ListAllStoreHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/ListAllStoresServlet", method = RequestMethod.GET)
	public String listAllStore(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum) {
		int pageSize = 5;    //每页的数据量
		PageBean<Store> pb = adminService.getPageStores(pageNum, pageSize);
		request.setAttribute("stores", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.getSession().setAttribute("status0", Contants.EXAMINEING);
		request.getSession().setAttribute("status1", Contants.PASS_EXAMINE);
		request.getSession().setAttribute("status2", Contants.REJECT_EXAMINE);
		request.getSession().setAttribute("status3", Contants.FORCE_CLOSE);
		return "admin/managerStores";
	}
}
