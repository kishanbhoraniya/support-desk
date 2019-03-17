package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.ITicketRequestHandler;
import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.TicketDesRes;
import com.finalhints.response.TicketRes;

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
	
//	@ResponseBody
//	@ResponseStatus(HttpStatus.OK)
//	@GetMapping
//	public Iterable<TicketRes> getAllTickets() {
//		return ticketRequestHandler.getAll();
//	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<TicketRes> getAllTicketsByUserId(@RequestParam(value = "user", required = true) int userId) {
		return ticketRequestHandler.getAll(userId);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{ticketId}")
	public TicketDesRes getTicket(@PathVariable(value = "ticketId", required = true) int ticketId) {
		return ticketRequestHandler.get(ticketId);
	}
}
