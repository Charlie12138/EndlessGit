package com.liqinglin.www.po;

import java.sql.Timestamp;

public class Store {
	private int storeId;//店铺id
	private String storeName;//店铺名字
	private String shopkeeperName;//店主姓名
	private String address;//店铺地址
	private String phone;//店铺电话
	private String storeDescription;//店铺描述
	private Timestamp createTime;//创建时间
	private int status;//店铺状态
	private User user;//店主

	public Store() {
	}

	public Store(int storeId, String storeName,
				 String shopkeeperName, String address,
				 String phone, String storeDescription,
				 Timestamp createTime, int status, User user) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.shopkeeperName = shopkeeperName;
		this.address = address;
		this.phone = phone;
		this.storeDescription = storeDescription;
		this.createTime = createTime;
		this.status = status;
		this.user = user;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getShopkeeperName() {
		return shopkeeperName;
	}

	public void setShopkeeperName(String shopkeeperName) {
		this.shopkeeperName = shopkeeperName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Store{" +
				"storeId=" + storeId +
				", storeName='" + storeName + '\'' +
				", shopkeeperName='" + shopkeeperName + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", storeDescription='" + storeDescription + '\'' +
				", createTime=" + createTime +
				", status=" + status +
				", user=" + user +
				'}';
	}
}
