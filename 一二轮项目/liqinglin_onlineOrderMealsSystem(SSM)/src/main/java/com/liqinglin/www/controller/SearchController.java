package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

	@Autowired
	StoreService storeService;

	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/SearchServlet", method = RequestMethod.GET)
	public String search(HttpServletRequest request) {
		String key = request.getParameter("key");//输入的关键字
		String choose = request.getParameter("choose");//选择搜索什么
		String jump = request.getParameter("jump");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = 5;//每页5个记录
		/**
		 * 检查输入是否为空并过滤%
		 */
		if(storeService.check(key)) {
			return null;
		}
		PageBean<Cuisine> pb = storeService.search(choose, key, pageNum, pageSize);
		if (pb.getList().size() == 0) {
			if((jump.equals("1"))) {
				return "/public/index";
			}
			if(jump.equals("2")) {
				return "user/userIndex";
			}
			if(jump.equals("3")) {
				return "store/storeIndex";
			}
		}
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("cuisines", pb.getList());
		if((jump.equals("1"))) {
			return "public/index";
		} else if(jump.equals("2")) {
			return "user/userIndex";
		} else if(jump.equals("3")) {
			return "store/storeIndex";
		}
		return null;
	}
}
