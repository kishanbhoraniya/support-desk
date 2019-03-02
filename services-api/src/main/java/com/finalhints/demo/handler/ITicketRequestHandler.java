package com.finalhints.demo.handler;

import com.finalhints.demo.request.Ticket.CreateTicketRq;
import com.finalhints.demo.response.CreatedRes;

public interface ITicketRequestHandler {

	CreatedRes create(CreateTicketRq createTicketRequest);

}
