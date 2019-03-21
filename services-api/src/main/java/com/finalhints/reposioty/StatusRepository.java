package com.finalhints.reposioty;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Status;

@Repository
public interface StatusRepository extends org.springframework.data.repository.CrudRepository<Status, Integer> {

	Optional<Status> findByName(String name);

}