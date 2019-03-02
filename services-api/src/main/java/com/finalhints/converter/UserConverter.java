package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.User;
import com.finalhints.request.user.CreateUserRq;
import com.finalhints.response.UserRes;

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
	public static final Function<User, UserRes> ENTITY_TO_RES = userEntity -> {
		UserRes res = new UserRes();
		res.setId(userEntity.getId());
		res.setFirstName(userEntity.getFirstName());
		res.setLastName(userEntity.getLastName());
		res.setNumber(userEntity.getNumber());
		res.setActive(userEntity.getActive() > 0);
		res.setRole(userEntity.getRole().getName());
		res.setEmail(userEntity.getEmail());
		res.setCreated(userEntity.getCreated());
		res.setUpdated(userEntity.getUpdated());
		return res;
	};

}
