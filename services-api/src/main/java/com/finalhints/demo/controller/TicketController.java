package com.finalhints.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.demo.handler.ITicketRequestHandler;
import com.finalhints.demo.request.Ticket.CreateTicketRq;
import com.finalhints.demo.response.CreatedRes;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	ITicketRequestHandler ticketRequestHandler;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreatedRes createTicket(@RequestBody @Validated CreateTicketRq createTicketRequest) {
		return ticketRequestHandler.create(createTicketRequest);
	}

}
