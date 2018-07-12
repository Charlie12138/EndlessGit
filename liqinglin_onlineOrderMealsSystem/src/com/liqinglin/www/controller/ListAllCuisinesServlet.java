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
 * Servlet implementation class ListAllCuisinesServlet
 */
@WebServlet("/ListAllCuisinesServlet")
public class ListAllCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));//当前页数
		String jump = request.getParameter("jump");
		StoreService storeService = new StoreService();
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(pageNum, pageSize);
		request.getSession().setAttribute("cuisines", pb.getList());		
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		if(jump.equals("1")) {
			request.getRequestDispatcher("/public/index.jsp").forward(request, response);
		} else if(jump.equals("2")){
			request.getRequestDispatcher("/user/userIndex.jsp").forward(request, response);
		}
	}

}
