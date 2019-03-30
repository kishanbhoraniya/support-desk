package com.finalhints.entity;

import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "updated", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne(targetEntity = Status.class)
	@JoinColumn(name = "status")
	Status status;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "assignee")
	User assigneeUser;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "created_by")
	User createdByUser;

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	Category category;
	
	@OneToMany(mappedBy = "ticket")
	private List<TicketReply> ticketReply;

	public List<TicketReply> getTicketReply() {
		return ticketReply;
	}

	public void setTicketReply(List<TicketReply> ticketReply) {
		this.ticketReply = ticketReply;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getAssigneeUser() {
		return assigneeUser;
	}

	public void setAssigneeUser(User assigneeUser) {
		this.assigneeUser = assigneeUser;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
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
