package com.finalhints.demo.request.Ticket;

import java.util.HashMap;
import java.util.Map;

public class CreateTicketRq {
	private int userId;
	private int categoryId;
	Map<String, Object> fieldMap = new HashMap<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Map<String, Object> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, Object> fieldMap) {
		this.fieldMap = fieldMap;
	}

}
