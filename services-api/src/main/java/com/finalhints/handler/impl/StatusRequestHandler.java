package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.TicketConverter;
import com.finalhints.entity.Category;
import com.finalhints.entity.Field;
import com.finalhints.entity.Project;
import com.finalhints.entity.Status;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.TicketDes;
import com.finalhints.entity.TicketReply;
import com.finalhints.entity.User;
import com.finalhints.handler.IStatusRequestHandler;
import com.finalhints.handler.ITicketRequestHandler;
import com.finalhints.reposioty.CategoryRepository;
import com.finalhints.reposioty.FieldRepository;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.StatusRepository;
import com.finalhints.reposioty.TicketDesRepository;
import com.finalhints.reposioty.TicketReplyRepository;
import com.finalhints.reposioty.TicketRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.requests.AssignTicketRq;
import com.finalhints.requests.ChangeTicketStatusRq;
import com.finalhints.requests.TicketReplyRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.TicketDesRes;
import com.finalhints.response.TicketRes;

@Service
public class StatusRequestHandler implements IStatusRequestHandler {
	
	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public Iterable<Status> getAll() {
		Iterable<Status> statusEntities = statusRepository.findAll();
		return statusEntities;
	}

}
