package com.finalhints.reposioty;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.Ticket;
import com.finalhints.entity.User;

@Repository
public interface TicketRepository extends org.springframework.data.repository.CrudRepository<Ticket, Integer> {
	@Query("select t from Ticket t where t.createdByUser = ?1")
	Iterable<Ticket> getTicketByUser(User user);

	@Query("select t from Ticket t where t.createdByUser = ?1")
	Iterable<Ticket> getTicketByUser(Optional<User> user);

	Iterable<Ticket> getTickets();
}