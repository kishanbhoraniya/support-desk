package com.finalhints.handler;

import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.response.CreatedRes;

public interface ITicketRequestHandler {

	CreatedRes create(CreateTicketRq createTicketRequest);

}
