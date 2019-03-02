package com.finalhints.request.category;

public class CreateCategoryRq {
	private String categoryName;
	private String categoryDes;
	private int projectId;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDes() {
		return categoryDes;
	}
	public void setCategoryDes(String categoryDes) {
		this.categoryDes = categoryDes;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
}
