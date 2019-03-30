package com.finalhints.reposioty;

import java.util.List;

import com.finalhints.entity.Project;
import com.finalhints.entity.Status;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.User;

public interface TicketCustomRepository {

	List<Ticket> getAllTickets(User user, List<User> createdBy, List<User> assignedTo, List<Status> status,
			List<Project> projects, Integer offset, Integer limit);
}
