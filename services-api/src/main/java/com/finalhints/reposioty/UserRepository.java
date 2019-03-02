package com.finalhints.reposioty;

import org.springframework.stereotype.Repository;


import com.finalhints.entity.User;

@Repository
public interface UserRepository extends org.springframework.data.repository.CrudRepository<User, Integer> {

}  