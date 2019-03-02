package com.finalhints.demo.handler;

import java.util.List;

import com.finalhints.demo.request.user.CreateUserRq;
import com.finalhints.demo.request.user.EditUserRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.demo.response.user.UserRes;

public interface IUserRequestHandler {
	CreatedRes create(CreateUserRq createUserRequest);

	CreatedRes edit(int id, EditUserRq editUserReq);

	List<UserRes> getAll();

	UserRes get(int id);

	CreatedRes delete(int id);
}
