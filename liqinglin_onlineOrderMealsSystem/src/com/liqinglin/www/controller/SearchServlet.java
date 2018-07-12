package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.service.StoreService;

/**
 *搜索的控制器
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreService storeService = new StoreService();
		String key = request.getParameter("key");//输入的关键字
		String choose = request.getParameter("choose");//选择搜索什么
		String jump = request.getParameter("jump");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = 5;//每页5个记录
		/**
		 * 检查输入是否为空并过滤%
		 */
		if(storeService.check(key)) {
			return;
		}
		PageBean<Cuisine> pb = storeService.search(choose, key, pageNum, pageSize);
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("cuisines", pb.getList());
		if((jump.equals("1"))) {
			request.getRequestDispatcher("/public/index.jsp").forward(request, response);
		} else if(jump.equals("2")) {
			request.getRequestDispatcher("/user/userIndex.jsp").forward(request, response);
		} else if(jump.equals("3")) {
			request.getRequestDispatcher("/store/storeIndex.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
