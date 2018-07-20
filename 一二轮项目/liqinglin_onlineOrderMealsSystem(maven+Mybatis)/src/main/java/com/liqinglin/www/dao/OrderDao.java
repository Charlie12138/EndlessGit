package com.liqinglin.www.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.po.SingleOrder;
import com.liqinglin.www.po.TotalOrder;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.MybatisUtil;
import com.liqinglin.www.util.OrderDaoContants;
import org.apache.ibatis.session.SqlSession;

public class OrderDao {

	/**
	 * 添加整个订单
	 * @param totalOrder
	 * @return
	 */

	public int addTotalOrder(TotalOrder totalOrder) {
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.ADDTOTALORDER;
		int result = session.insert(statement, totalOrder);
		session.close();
		return result;
	}


	/**
	 * 删除订单
	 * @param orderId
	 */

	public void deleteOrder(int orderId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.DELETEORDER;
		session.delete(statement, orderId);
		session.close();
	}


	/**
	 * 获得总订单
	 * @param orderNum
	 * @return
	 */
	public TotalOrder getTotalOrder(String orderNum) {
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.GETTOTALORDER;
		TotalOrder totalOrder = session.selectOne(statement, orderNum);
		session.close();
		return  totalOrder;
	}


	/**
	 * 添加单个商品订单
	 * @param singleOrder
	 * @return
	 */
	public int addSingleOrder(SingleOrder singleOrder) {
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.ADDSINGLEORDER;
		int result = session.insert(statement, singleOrder);
		session.close();
		return result;
	}


	/**
	 * 获得订单中单个商品订单的总数
	 * @param orderId
	 * @return
	 */
	public int getStoreAllSingleOrderNum(int orderId){
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.GETSINGLENUM;
		int totalRecord = session.selectOne(statement, orderId);
		session.close();
		return totalRecord;
	}


	/**
	 * 分页：获得某一页所需要的单个商品订单数据
	 * @param orderId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<SingleOrder> getPageSingleOrder(int orderId, int startIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.GET_PAGE_SINGLE;
		List<SingleOrder> singleOrders = session.selectList(statement, map);
		session.close();
		return singleOrders;
	}


	/**
	 * 修改订单状态
	 * @param status
	 * @param id
	 */
	public void modifyOrderStatus(int status, int id) {
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.MODIFY_ORDER_STATUS;
		TotalOrder totalOrder = new TotalOrder();
		totalOrder.setStatus(status);
		totalOrder.setId(id);
		session.update(statement, totalOrder);
		session.close();
	}


	/**
	 * 获得店铺符合要求订单的数量
	 * @param status
	 * @param id
	 * @param roleSelect
	 * @return
	 */

	public int getStoreOrderCount(int status, int id, int roleSelect){
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.GET_STORE_ORDERNUM_BY_STOREID;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("status", status);
		map.put("id", id);
		int totalRecord = 0;
		if(roleSelect == Contants.STORE_ORDER) {  //同一个店铺的订单
			totalRecord = session.selectOne(statement, map);
		} else if(roleSelect == Contants.USER_ORDER) { //同一个用户的订单
			statement = OrderDaoContants.GET_STORE_ORDERNUM_BY_USERID;
			totalRecord = session.selectOne(statement, map);
		}
		session.close();
		return totalRecord;
	}


	/**
	 * 分页：获得某一页所需要的订单数据
	 * @param status
	 * @param id
	 * @param startIndex
	 * @param pageSize
	 * @param roleSelect
	 * @return
	 */
	public List<TotalOrder> getPageOrder(int status, int id, int startIndex, int pageSize, int roleSelect) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("status", status);
		map.put("id", id);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<TotalOrder> totalOrders = null;
		SqlSession session = MybatisUtil.getSession();
		String statement = OrderDaoContants.GET_PAGEORDER_BY_STOREID;
		if(roleSelect == Contants.STORE_ORDER) {  //同一个店铺的订单
			totalOrders = session.selectList(statement, map);
		} else if(roleSelect == Contants.USER_ORDER) { //同一个用户的订单
			statement = OrderDaoContants.GET_PAGEORDER_BY_USERID;
			totalOrders = session.selectList(statement, map);
		}
		session.close();
		return totalOrders;
	}
	
}
