package com.finalhints.reposioty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.Project;
import com.finalhints.entity.User;

@Repository
public interface ProjectRepository extends org.springframework.data.repository.CrudRepository<Project, Integer> {

	@Query("select p from Project p INNER JOIN p.projectRoles pr where pr.user=?1 AND (pr.role.name='admin' OR pr.role.name='manager')")
	Iterable<Project> findProjectForUser(User currentUser);

}