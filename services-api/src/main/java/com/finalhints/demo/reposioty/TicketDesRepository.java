package com.finalhints.demo.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.TicketDes;

@Repository
public interface TicketDesRepository extends org.springframework.data.repository.CrudRepository<TicketDes, Integer> {

}