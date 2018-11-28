package com.liqinglin.www.po;

import java.sql.Timestamp;
import java.util.List;

public class TotalOrder {
	private int id;//订单主键id
	private String orderNum;//订单编号
	private Double totalPrice;//订单总价钱
	private int userId;//用户id
	private String receiver;//收货人
	private String phone;//收货人电话
	private String address;//收货地址
	private String message;//用户留言
	private Timestamp createTime;//创建时间
	private int status;//订单状态
	private List<SingleOrder> singleOrders;//属于这个店铺的商品集合
	private Store store;

	public TotalOrder() {
	}

	public TotalOrder(int id, String orderNum, Double totalPrice,
		int userId, String receiver, String phone, String address,
		String message, Timestamp createTime, int status, List<SingleOrder> singleOrders, Store store) {
		this.id = id;
		this.orderNum = orderNum;
		this.totalPrice = totalPrice;
		this.userId = userId;
		this.receiver = receiver;
		this.phone = phone;
		this.address = address;
		this.message = message;
		this.createTime = createTime;
		this.status = status;
		this.singleOrders = singleOrders;
		this.store = store;
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

	public List<SingleOrder> getSingleOrders() {
		return singleOrders;
	}

	public void setSingleOrders(List<SingleOrder> singleOrders) {
		this.singleOrders = singleOrders;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "TotalOrder{" +
				"id=" + id +
				", orderNum='" + orderNum + '\'' +
				", totalPrice=" + totalPrice +
				", userId=" + userId +
				", receiver='" + receiver + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", message='" + message + '\'' +
				", createTime=" + createTime +
				", status=" + status +
				", singleOrders=" + singleOrders +
				", store=" + store +
				'}';
	}
}
