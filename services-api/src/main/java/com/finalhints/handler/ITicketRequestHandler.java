package com.finalhints.handler;

import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.requests.AssignTicketRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.TicketDesRes;
import com.finalhints.response.TicketRes;

public interface ITicketRequestHandler {

	CreatedRes create(CreateTicketRq createTicketRequest);

	Iterable<TicketRes> getAll();
	
	Iterable<TicketRes> getAll(int userId);

	TicketDesRes setAssignee(AssignTicketRq assignTicketRequest);

	TicketDesRes get(int ticketId);

}
