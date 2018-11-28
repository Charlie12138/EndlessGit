package com.liqinglin.www.service;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;

public interface OrderService {
	boolean check(TotalOrder totalOrder);

	boolean check(String address, String message, String phone, String receiver);

	int addTotalOrder(TotalOrder totalOrder);

	void deleteTotalOrder(int orderId);

	TotalOrder getTotalOrder(String orderNum);

	int addSingleOrder(SingleOrder singleOrder);

	PageBean<SingleOrder> getSingleOrder(int orderId, int pageNum, int pageSize);

	void modifyOrderStatus(int status, int id);

	PageBean<TotalOrder> getOrder(int status, int id, int pageNum, int pageSize, int roleSelect);
}
