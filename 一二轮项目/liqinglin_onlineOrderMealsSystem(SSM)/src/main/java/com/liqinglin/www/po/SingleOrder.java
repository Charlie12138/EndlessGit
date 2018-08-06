package com.liqinglin.www.po;

public class SingleOrder {
	private int id;//主键
	private int orderId;//总订单Id
	private int storeId;//店铺Id
	private int number;//商品数量
	private Double totalPrice;//总价格
	private Double singlePrice;//单品价格
	private Cuisine cuisine;

	public SingleOrder() {
	}

	public SingleOrder(int id, int orderId, int storeId, int number, Double totalPrice, Double singlePrice, Cuisine cuisine) {
		this.id = id;
		this.orderId = orderId;
		this.storeId = storeId;
		this.number = number;
		this.totalPrice = totalPrice;
		this.singlePrice = singlePrice;
		this.cuisine = cuisine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	@Override
	public String toString() {
		return "SingleOrder{" +
				"id=" + id +
				", orderId=" + orderId +
				", storeId=" + storeId +
				", number=" + number +
				", totalPrice=" + totalPrice +
				", singlePrice=" + singlePrice +
				", cuisine=" + cuisine +
				'}';
	}
}
