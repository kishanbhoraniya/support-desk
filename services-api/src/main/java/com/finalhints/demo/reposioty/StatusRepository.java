package com.finalhints.demo.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Status;


@Repository
public interface StatusRepository extends org.springframework.data.repository.CrudRepository<Status, Integer> {

}