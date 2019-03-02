package com.finalhints.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Ticket;

@Repository
public interface TicketRepository extends org.springframework.data.repository.CrudRepository<Ticket, Integer> {

}