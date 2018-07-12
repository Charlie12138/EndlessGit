package com.liqinglin.www.po;

import java.sql.Timestamp;
import java.util.List;

public class TotalOrder {
	private int id;//订单主键id
	private String orderNum;//订单编号
	private int storeId;//店铺id
	private Double totalPrice;//订单总价钱
	private int userId;//用户id
	private String receiver;//收货人
	private String phone;//收货人电话
	private String address;//收货地址
	private String message;//用户留言
	private Timestamp createTime;//创建时间
	private int status;//订单状态
	private String storeName;//店铺名
	private List<SingleOrder> singleOrders;//属于这个店铺的商品集合
	
	public List<SingleOrder> getSingleOrders() {
		return singleOrders;
	}
	public void setSingleOrders(List<SingleOrder> singleOrders) {
		this.singleOrders = singleOrders;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
}
