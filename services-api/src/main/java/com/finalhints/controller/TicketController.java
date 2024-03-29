package com.finalhints.controller;

import java.util.List;

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
import com.finalhints.requests.AssignTicketRq;
import com.finalhints.requests.ChangeTicketStatusRq;
import com.finalhints.requests.TicketReplyRq;
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

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<TicketRes> getAllTickets(@RequestParam(value = "currentUser", required = false) Integer userId,
			@RequestParam(value = "createdBy", required = false) List<Integer> createdBy,
			@RequestParam(value = "assignedTo", required = false) List<Integer> assignedTo,
			@RequestParam(value = "status", required = false) List<String> status,
			@RequestParam(value = "projects", required = false) List<Integer> projects,
			@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit) {
		return ticketRequestHandler.getAllTicket(userId, createdBy, assignedTo, status, projects, offset, limit);
	}

	// @ResponseBody
	// @ResponseStatus(HttpStatus.OK)
	// @GetMapping
	// public Iterable<TicketRes> getAllTicketsByUserId(@RequestParam(value =
	// "user", required = true) int userId) {
	// return ticketRequestHandler.getAll(userId);
	// }

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{ticketId}")
	public TicketDesRes getTicket(@PathVariable(value = "ticketId", required = true) int ticketId) {
		return ticketRequestHandler.get(ticketId);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/assign")
	public TicketDesRes assignTicket(@RequestBody @Validated AssignTicketRq assignTicketRequest) {
		return ticketRequestHandler.setAssignee(assignTicketRequest);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/status")
	public TicketDesRes changeTicketStatus(@RequestBody @Validated ChangeTicketStatusRq changeTicketStatusRequest) {
		return ticketRequestHandler.setStatus(changeTicketStatusRequest);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/reply")
	public CreatedRes ticketReply(@RequestBody @Validated TicketReplyRq ticketReplyRequest) {
		return ticketRequestHandler.setTicketReply(ticketReplyRequest);
	}
}
