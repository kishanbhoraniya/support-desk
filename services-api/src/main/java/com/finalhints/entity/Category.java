package com.finalhints.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "des")
	private String categoryDes;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class)
	@JoinColumn(name = "project_id")
	Project project;

	@OneToMany(mappedBy = "category")
	private List<Field> categoryFields;

	public List<Field> getCategoryFields() {
		return categoryFields;
	}

	public void setCategoryFields(List<Field> categoryField) {
		this.categoryFields = categoryField;
	}

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

	public String getCategoryDes() {
		return categoryDes;
	}

	public void setCategoryDes(String categoryDes) {
		this.categoryDes = categoryDes;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
