package com.finalhints.response;

import java.util.Date;

public class ProjectRes {

	private int id;
	private String name;
	private String description;
	private String adminName;
	private int adminUserId;
	private Date created;
	private Date updated;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String admin_name) {
		this.adminName = admin_name;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int admin_user_id) {
		this.adminUserId = admin_user_id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
