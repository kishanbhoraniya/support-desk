package com.finalhints.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Project;

@Repository
public interface ProjectRepository extends org.springframework.data.repository.CrudRepository<Project, Integer> {

}