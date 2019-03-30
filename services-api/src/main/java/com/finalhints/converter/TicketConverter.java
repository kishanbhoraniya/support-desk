package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Ticket;
import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.response.TicketRes;

public final class TicketConverter {

	public static final Function<CreateTicketRq, Ticket> CREATE_TO_ENTITY = createTicketReq -> {

		Ticket ticket = new Ticket();
		return ticket;
	};

	public static final Function<Ticket, TicketRes> ENTITY_TO_RES = ticketEntity -> {
		TicketRes res = new TicketRes();
		res.setId(ticketEntity.getId());
		res.setCategory(ticketEntity.getCategory().getName());
		res.setProject(ticketEntity.getCategory().getProject().getName());
		res.setProjectId(ticketEntity.getCategory().getProject().getId());
		res.setStatus(ticketEntity.getStatus().getName());
		res.setCreatedBy(
				ticketEntity.getCreatedByUser().getFirstName() + " " + ticketEntity.getCreatedByUser().getLastName());
		res.setAssignee(
				ticketEntity.getAssigneeUser().getFirstName() + " " + ticketEntity.getAssigneeUser().getLastName());
		res.setCreated(ticketEntity.getCreated());
		res.setUpdated(ticketEntity.getUpdated());
		return res;
	};

}
