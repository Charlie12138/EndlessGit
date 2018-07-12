package com.liqinglin.www.service;

import java.util.ArrayList;
import java.util.List;

import com.liqinglin.www.dao.OrderDao;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.util.Contants;

public class OrderService {
	
	OrderDao orderDao = new OrderDao();
	
	/**
	 * 检查输入是否为空
	 * @param totalOrder
	 * @return
	 */
	public boolean check(TotalOrder totalOrder) {
		if("".equals(totalOrder.getReceiver()) || "".equals(totalOrder.getAddress()) || "".equals(totalOrder.getPhone())){
			return true;
		}
		return false;
	}
	
	public boolean check(String address, String message, String phone, String receiver) {
		if("".equals(receiver) || "".equals(address) || "".equals(phone)){
			return true;
		}
		return false;
	}
	
	/**
	 * 添加整个订单的业务
	 */
	public int addTotalOrder(TotalOrder totalOrder) {
		int result = orderDao.addTotalOrder(totalOrder);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 删除订单的业务
	 */
	public void deleteTotalOrder(int orderId) {
		orderDao.deleteOrder(orderId);
	}
	
	/**
	 * 获得总订单
	 */
	public TotalOrder getTotalOrder(String orderNum) {
		TotalOrder totalOrder = orderDao.getTotalOrder(orderNum);
		return totalOrder;
	}
	
	/**
	 * 添加单个商品的订单
	 */
	public int addSingleOrder(SingleOrder singleOrder) {
		int result = orderDao.addSingleOrder(singleOrder);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 获得单个商品的订单并为分页做准备
	 */
	public PageBean<SingleOrder> getSingleOrder(int orderId, int pageNum, int pageSize){
		int totalRecord = orderDao.getStoreAllSingleOrderNum(orderId);    //获得数据量
		PageBean<SingleOrder> pb = new PageBean<SingleOrder>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		List<SingleOrder> singleOrders = orderDao.getPageSingleOrder(orderId, startIndex, pageSize);		
		pb.setList(singleOrders);
		return pb;
	}
	
	/**
	 * 修改订单状态
	 */
	public void modifyOrderStatus(int status, int id) {
		orderDao.modifyOrderStatus(status, id);
	}
	
	/**
	 * 获得商店订单并为分页做准备
	 */
	public PageBean<TotalOrder> getOrder(int status, int id, int pageNum, int pageSize, int roleSelect){
		int totalRecord = orderDao.getStoreOrderCount(status, id, roleSelect);   //获得数据量
		PageBean<TotalOrder> pb = new PageBean<TotalOrder>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		List<TotalOrder> totalOrders = orderDao.getPageOrder(status, id, startIndex, pageSize, roleSelect);
		List<TotalOrder> notSendOrders = new ArrayList<TotalOrder>();
		/**
		 * 获得未处理的订单
		 */
		for(TotalOrder totalOrder : totalOrders) {
			if(totalOrder.getStatus() == status) {
				notSendOrders.add(totalOrder);
			}
		}
		pb.setList(notSendOrders);
		return pb;
	}	
	
}
