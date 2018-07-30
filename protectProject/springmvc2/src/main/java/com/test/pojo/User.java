package com.test.pojo;

public class User {
	private int id;
	private int age;
	private String username;
	private String password;
	private Address address;

	public User() {
	}

	public User(int id, int age, String username, String password) {
		this.id = id;
		this.age = age;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", age=" + age +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", address=" + address +
				'}';
	}
}
