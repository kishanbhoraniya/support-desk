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
import com.finalhints.entity.Status;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.TicketDes;
import com.finalhints.entity.User;
import com.finalhints.handler.ITicketRequestHandler;
import com.finalhints.reposioty.CategoryRepository;
import com.finalhints.reposioty.FieldRepository;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.StatusRepository;
import com.finalhints.reposioty.TicketDesRepository;
import com.finalhints.reposioty.TicketRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.ticket.CreateTicketRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.TicketDesRes;
import com.finalhints.response.TicketRes;

@Service
public class TicketRequestHandler implements ITicketRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketDesRepository ticketDesRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	FieldRepository fieldRepository;

	@Override
	public CreatedRes create(CreateTicketRq createTicketRequest) {

		Ticket ticket = TicketConverter.CREATE_TO_ENTITY.apply(createTicketRequest);
		Status status = statusRepository.findById(1).get();
		User creator = userRepository.findById(createTicketRequest.getUserId()).get();
		User assignee = userRepository.findById(1).get();
		Category category = categoryRepository.findById(createTicketRequest.getCategoryId()).get();
		ticket.setAssigneeUser(assignee);
		ticket.setCreatedByUser(creator);
		ticket.setStatus(status);
		ticket.setCategory(category);
		ticketRepository.save(ticket);

		List<TicketDes> fieldValues = new ArrayList<TicketDes>();
		for (Entry<String, Object> entry : createTicketRequest.getFieldMap().entrySet()) {
			Field field = fieldRepository.findById(Integer.parseInt(entry.getKey())).get();
			TicketDes ticketDes = new TicketDes();
			ticketDes.setField(field);
			ticketDes.setTicket(ticket);
			ticketDes.setValue(String.valueOf(entry.getValue()));
			fieldValues.add(ticketDes);
		}
		ticketDesRepository.saveAll(fieldValues);

		return new CreatedRes(ticket.getId());
	}

	@Override
	public Iterable<TicketRes> getAll() {
		Iterable<Ticket> ticketEntities = ticketRepository.findAll();
		List<TicketRes> res = new ArrayList<>();
		Iterator<Ticket> iterator = ticketEntities.iterator();
		while (iterator.hasNext()) {
			res.add(TicketConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return res;
	}

	@Override
	public Iterable<TicketRes> getAll(int userId) {
		Optional<User> user = userRepository.findById(userId);

		Iterable<Ticket> ticketEntities = ticketRepository.getTicketByUser(user);
		List<TicketRes> res = new ArrayList<>();
		Iterator<Ticket> iterator = ticketEntities.iterator();
		while (iterator.hasNext()) {
			res.add(TicketConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return res;

	}

	@Override
	public TicketDesRes get(int ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).get();
		TicketDesRes ticketDesRes = new TicketDesRes();
		ticketDesRes.setId(ticketId);
		ticketDesRes.setCreated(ticket.getCreated());
		ticketDesRes.setUpdated(ticket.getUpdated());
		ticketDesRes
				.setAssignee(ticket.getAssigneeUser().getFirstName() + " " + ticket.getAssigneeUser().getLastName());
		ticketDesRes.setCategory(ticket.getCategory().getName());
		ticketDesRes
				.setCreatedBy(ticket.getCreatedByUser().getFirstName() + " " + ticket.getCreatedByUser().getLastName());
		ticketDesRes.setStatus(ticket.getStatus().getName());
		ticketDesRes.setProject(ticket.getCategory().getProject().getName());
		List<TicketDesRes.Fields> fieldsres = new ArrayList<>();
		List<Field> fields = ticket.getCategory().getCategoryFields();

		Iterator<Field> iterator = fields.iterator();
		while (iterator.hasNext()) {
			Field f = iterator.next();
			TicketDesRes.Fields field = ticketDesRes.new Fields();
			field.setId(f.getId());
			field.setName(f.getName());
			field.setDesc(f.getDes());
			field.setType(f.getType());
			TicketDes ticketDes = ticketDesRepository.getTicketDescByTicketAndField(ticket, f).get();
			field.setValue(ticketDes.getValue());
			fieldsres.add(field);
		}
		ticketDesRes.setFields(fieldsres);
		return ticketDesRes;
	}

}
