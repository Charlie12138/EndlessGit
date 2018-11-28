package com.liqinglin.www.dao;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

	/**
	 * 添加整个订单
	 * @param totalOrder
	 * @return
	 */
	int addTotalOrder(TotalOrder totalOrder);

	/**
	 * 删除订单
	 * @param orderId
	 */
	void deleteOrder(int orderId);

	/**
	 * 获得总订单
	 * @param orderNum
	 * @return
	 */
	TotalOrder getTotalOrder(String orderNum);

	/**
	 * 添加单个商品订单
	 * @param singleOrder
	 * @return
	 */
	int addSingleOrder(SingleOrder singleOrder);

	/**
	 * 获得订单中单个商品订单的总数
	 * @param orderId
	 * @return
	 */
	int getStoreAllSingleOrderNum(int orderId);

	/**
	 * 分页：获得某一页所需要的单个商品订单数据
	 */
	List<SingleOrder> getPageSingleOrder(Map<String, Integer> map);

	/**
	 * 修改订单状态
	 */
	void modifyOrderStatus(TotalOrder totalOrder);

	/**
	 * 获得店铺符合要求订单的数量
	 */
	int getOrderCountByStoreId(Map<String, Integer> map);

	int getOrderCountByUserId(Map<String, Integer> map);

	List<TotalOrder> getPageOrderByStoreId(Map<String, Integer> map);

	List<TotalOrder> getPageOrderByUserId(Map<String, Integer> map);

}
