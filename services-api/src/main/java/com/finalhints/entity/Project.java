package com.finalhints.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "project")
public class Project {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "admin_user_id")
	User admin;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "created_by")
	User createdBy;

	@OneToMany(mappedBy = "project")
	private List<Category> projectsCategories;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "updated", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
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

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<Category> getProjectsCategories() {
		return projectsCategories;
	}

	public void setProjectsCategories(List<Category> projectsCategory) {
		this.projectsCategories = projectsCategory;
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

	@PrePersist
	void createdAt() {
		this.created = this.updated = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updated = new Date();
	}

}
