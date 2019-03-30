package com.finalhints.reposioty;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.Role;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.TicketReply;

@Repository
public interface TicketReplyRepository extends org.springframework.data.repository.CrudRepository<TicketReply, Integer> {
	
	List<TicketReply> findByTicket(Ticket ticket);
}