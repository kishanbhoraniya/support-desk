package com.finalhints.response;

import java.util.List;

public class CategoryRes {
	private int id;
	private String name;
	private String desc;
	List<FieldRes> fields;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<FieldRes> getFields() {
		return fields;
	}

	public void setFields(List<FieldRes> fields) {
		this.fields = fields;
	}

}
