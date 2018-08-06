package com.liqinglin.www.po;

public class CartInfo {
	private int id;//主键id
	private Cuisine cuisine;//商品
	private Cart cart;//所属购物车
	private Store store;//店铺
	private int number;//商品数量
	private double totalPrice;//总价格
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double d) {
		this.totalPrice = d;
	}
	
	
}
