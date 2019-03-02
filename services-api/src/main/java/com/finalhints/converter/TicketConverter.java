package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Ticket;
import com.finalhints.request.ticket.CreateTicketRq;

public final class TicketConverter {

	public static final Function<CreateTicketRq, Ticket> CREATE_TO_ENTITY = createTicketReq -> {

		
		Ticket ticket = new Ticket();
		return ticket;
	};

}
