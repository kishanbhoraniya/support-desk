package com.finalhints.demo.converter;

import java.util.function.Function;

import com.finalhints.demo.request.user.CreateUserRq;
import com.finalhints.entity.User;

public final class UserConverter {

	public static final Function<CreateUserRq, User> CREATE_TO_ENTITY = createUserReq -> {
		User user = new User();
		user.setFirstName(createUserReq.getFirstName());
		user.setLastName(createUserReq.getLastName());
		user.setNumber(createUserReq.getNumber());
		user.setEmail(createUserReq.getEmail());
		user.setPassword(createUserReq.getPassword());
		return user;
	};

}
