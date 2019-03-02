package com.finalhints.demo.converter;

import java.util.function.Function;

import com.finalhints.demo.request.Ticket.CreateTicketRq;
import com.finalhints.entity.Ticket;

public final class TicketConverter {

	public static final Function<CreateTicketRq, Ticket> CREATE_TO_ENTITY = createTicketReq -> {

		
		Ticket ticket = new Ticket();
		return ticket;
	};

}
