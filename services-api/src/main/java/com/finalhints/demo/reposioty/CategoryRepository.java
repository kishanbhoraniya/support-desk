package com.finalhints.demo.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Category;

@Repository
public interface CategoryRepository extends org.springframework.data.repository.CrudRepository<Category, Integer> {

}