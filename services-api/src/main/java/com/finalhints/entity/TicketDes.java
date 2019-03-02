package com.finalhints.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_des")
public class TicketDes {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ticket.class)
	@JoinColumn(name = "ticket_id")
	Ticket ticket;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Field.class)
	@JoinColumn(name = "field_id")
	Field field;

	@Column(name = "value")
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticketid) {
		this.ticket = ticketid;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field fieldId) {
		this.field = fieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
