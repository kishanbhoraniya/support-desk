package com.finalhints.demo.reposioty;

import org.springframework.stereotype.Repository;

import com.finalhints.entity.Role;

@Repository
public interface RoleRepository extends org.springframework.data.repository.CrudRepository<Role, Integer> {

}  
