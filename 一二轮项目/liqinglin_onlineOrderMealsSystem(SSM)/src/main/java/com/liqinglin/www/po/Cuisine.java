package com.liqinglin.www.po;

import java.sql.Timestamp;

public class Cuisine {
	private int id;
	private String cuisineName;
	private double price;
	private String description;
	private String picturePath;
	private int status;
	private String storeName;
	private Timestamp createTime;
	private Store store;//所属店铺
	private int sellCount;//销量

	public Cuisine() {
	}

	public Cuisine(int id, String cuisineName,
				   double price, String description,
				   String picturePath, int status, String storeName,
				   Timestamp createTime, Store store, int sellCount) {
		this.id = id;
		this.cuisineName = cuisineName;
		this.price = price;
		this.description = description;
		this.picturePath = picturePath;
		this.status = status;
		this.storeName = storeName;
		this.createTime = createTime;
		this.store = store;
		this.sellCount = sellCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	@Override
	public String toString() {
		return "Cuisine{" +
				"id=" + id +
				", cuisineName='" + cuisineName + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", picturePath='" + picturePath + '\'' +
				", status=" + status +
				", storeName='" + storeName + '\'' +
				", createTime=" + createTime +
				", store=" + store +
				", sellCount=" + sellCount +
				'}';
	}
}
