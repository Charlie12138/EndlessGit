package com.liqinglin.www.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.dao.OrderMapper;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	
	/**
	 * 检查输入是否为空
	 * @param totalOrder
	 * @return
	 */
	@Override
	public boolean check(TotalOrder totalOrder) {
		if("".equals(totalOrder.getReceiver()) || "".equals(totalOrder.getAddress()) || "".equals(totalOrder.getPhone())){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean check(String address, String message, String phone, String receiver) {
		if("".equals(receiver) || "".equals(address) || "".equals(phone)){
			return true;
		}
		return false;
	}
	
	/**
	 * 添加整个订单的业务
	 */
	@Override
	public int addTotalOrder(TotalOrder totalOrder) {
		int result = orderMapper.addTotalOrder(totalOrder);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 删除订单的业务
	 */
	@Override
	public void deleteTotalOrder(int orderId) {
		orderMapper.deleteOrder(orderId);
	}
	
	/**
	 * 获得总订单
	 */
	@Override
	public TotalOrder getTotalOrder(String orderNum) {
		TotalOrder totalOrder = orderMapper.getTotalOrder(orderNum);
		return totalOrder;
	}
	
	/**
	 * 添加单个商品的订单
	 */
	@Override
	public int addSingleOrder(SingleOrder singleOrder) {
		int result = orderMapper.addSingleOrder(singleOrder);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 获得单个商品的订单并为分页做准备
	 */
	@Override
	public PageBean<SingleOrder> getSingleOrder(int orderId, int pageNum, int pageSize){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int totalRecord = orderMapper.getStoreAllSingleOrderNum(orderId);    //获得数据量
		PageBean<SingleOrder> pb = new PageBean<SingleOrder>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		map.put("orderId", orderId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<SingleOrder> singleOrders = orderMapper.getPageSingleOrder(map);
		pb.setList(singleOrders);
		return pb;
	}
	
	/**
	 * 修改订单状态
	 */
	@Override
	public void modifyOrderStatus(int status, int id) {
		TotalOrder totalOrder = new TotalOrder();
		totalOrder.setStatus(status);
		totalOrder.setId(id);
		orderMapper.modifyOrderStatus(totalOrder);
	}
	
	/**
	 * 获得商店订单并为分页做准备
	 */
	@Override
	public PageBean<TotalOrder> getOrder(int status, int id, int pageNum, int pageSize, int roleSelect){
		int totalRecord = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("status", status);
		map.put("id", id);
		if(roleSelect == Contants.STORE_ORDER) {  //同一个店铺的订单
			totalRecord = orderMapper.getOrderCountByStoreId(map);
		} else if(roleSelect == Contants.USER_ORDER) { //同一个用户的订单
			totalRecord = orderMapper.getOrderCountByUserId(map);
		}

		PageBean<TotalOrder> pb = new PageBean<TotalOrder>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<TotalOrder> totalOrders = null;
		if(roleSelect == Contants.STORE_ORDER) {  //同一个店铺的订单
			totalOrders = orderMapper.getPageOrderByStoreId(map);
		} else if(roleSelect == Contants.USER_ORDER) { //同一个用户的订单
			totalOrders = orderMapper.getPageOrderByUserId(map);
		}
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
