package com.finalhints.handler;

import java.util.List;

import com.finalhints.entity.TicketReply;
import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.requests.AssignTicketRq;
import com.finalhints.requests.ChangeTicketStatusRq;
import com.finalhints.requests.TicketReplyRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.TicketDesRes;
import com.finalhints.response.TicketRes;

public interface ITicketRequestHandler {

	CreatedRes create(CreateTicketRq createTicketRequest);

	Iterable<TicketRes> getAll();

	Iterable<TicketRes> getAll(int userId);

	Iterable<TicketRes> getAllTicket(Integer userId, List<Integer> createdBy, List<Integer> assignedTo,
			List<String> status, List<Integer> projects, Integer offset, Integer limit);

	TicketDesRes setAssignee(AssignTicketRq assignTicketRequest);
	
	TicketDesRes setStatus(ChangeTicketStatusRq changeTicketStatusRequest);

	TicketDesRes get(int ticketId);
	
	CreatedRes setTicketReply(TicketReplyRq ticketReplyRequest);

}
