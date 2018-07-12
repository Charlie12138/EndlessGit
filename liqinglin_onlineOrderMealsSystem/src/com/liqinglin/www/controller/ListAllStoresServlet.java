package com.liqinglin.www.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;

/**
 * 列出所有店家的控制器
 */
@WebServlet("/ListAllStoresServlet")
public class ListAllStoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void listAllStores(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));//当前页数
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
		try {
			request.getRequestDispatcher("/admin/managerStores.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listAllStores(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}