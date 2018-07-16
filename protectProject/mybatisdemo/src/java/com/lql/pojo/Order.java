package com.lql.pojo;

public class Order {
	private int id;
	private String OrderNo;
	private float price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Order() {
		super();
	}

	public Order(int id, String orderNo, float price) {
		this.id = id;
		OrderNo = orderNo;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", OrderNo='" + OrderNo + '\'' +
				", price=" + price +
				'}';
	}
}
