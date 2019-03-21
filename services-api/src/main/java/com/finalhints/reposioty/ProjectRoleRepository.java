package com.finalhints.reposioty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.Project;
import com.finalhints.entity.ProjectRole;
import com.finalhints.entity.User;

@Repository
public interface ProjectRoleRepository
		extends org.springframework.data.repository.CrudRepository<ProjectRole, Integer> {

	@Query("select pr.user from ProjectRole pr where pr.project = ?1 AND pr.role='admin'")
	User getAdmin(Project project);

}