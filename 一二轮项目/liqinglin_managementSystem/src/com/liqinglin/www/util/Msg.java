package com.liqinglin.www.util;

public class Msg {
	private String result;
	private Object message;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Msg() {
		super();
	}

	public Msg(String result, Object message) {
		super();
		this.message = message;
		this.result = result;
	}

	public String toString() {
		return "Msg [result=" + result + ", message=" + message + "]";
	}
}
