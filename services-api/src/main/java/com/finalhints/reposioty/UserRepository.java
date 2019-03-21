package com.finalhints.reposioty;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalhints.entity.User;

@Repository
public interface UserRepository extends org.springframework.data.repository.CrudRepository<User, Integer> {

	@Query("select u from User u where u.email = ?1 and u.password=?2")
	Optional<User> getUserByEmailAndPassword(String email, String password);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);
}