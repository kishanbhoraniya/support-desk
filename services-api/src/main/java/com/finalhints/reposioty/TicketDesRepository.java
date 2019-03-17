package com.finalhints.reposioty;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.Field;
import com.finalhints.entity.Ticket;
import com.finalhints.entity.TicketDes;

@Repository
public interface TicketDesRepository extends org.springframework.data.repository.CrudRepository<TicketDes, Integer> {

	@Query("select td from TicketDes td where td.ticket = ?1 and td.field=?2")
	Optional<TicketDes> getTicketDescByTicketAndField(Ticket ticket, Field field);
}