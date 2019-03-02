package com.finalhints.demo.response;

public class CreatedRes {

	private int id;
	private boolean success;

	public CreatedRes(boolean success, int id) {
		this.id = id;
		this.success = success;
	}

	public CreatedRes(int id) {
		this(true, id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
