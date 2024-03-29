package com.finalhints.reposioty;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Role;

@Repository
public interface RoleRepository extends org.springframework.data.repository.CrudRepository<Role, Integer> {

	Optional<Role> findByName(String name);
}
