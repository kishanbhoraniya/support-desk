package com.finalhints.request.field;

public class CreateFieldRq {
	private String fieldName;
	private String fieldDes;
	private String type;
	private boolean required;
	private int categoryId;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDes() {
		return fieldDes;
	}
	public void setFieldDes(String fieldDes) {
		this.fieldDes = fieldDes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
