package com.finalhints.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Field;

@Repository
public interface FieldRepository extends org.springframework.data.repository.CrudRepository<Field, Integer> {

}