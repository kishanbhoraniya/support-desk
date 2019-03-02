package com.finalhints.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "number")
	private String number;

	@Column(name = "active")
	private byte active;

	@OneToOne
	@JoinColumn(name = "role")
	private Role role;

	@OneToMany(mappedBy = "admin")
	private List<Project> adminProjects;

	@OneToMany(mappedBy = "createdBy")
	private List<Project> createdProject;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "updated", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Project> getAdminProjects() {
		return adminProjects;
	}

	public void setAdminProjects(List<Project> adminProjects) {
		this.adminProjects = adminProjects;
	}

	public List<Project> getCreatedProject() {
		return createdProject;
	}

	public void setCreatedProject(List<Project> createdProject) {
		this.createdProject = createdProject;
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
