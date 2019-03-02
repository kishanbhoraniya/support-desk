package com.finalhints.handler;

import com.finalhints.request.user.CreateUserRq;
import com.finalhints.request.user.EditUserRq;
import com.finalhints.request.user.LoginReq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.UserRes;

public interface IUserRequestHandler {
	CreatedRes create(CreateUserRq createUserRequest);

	CreatedRes edit(int id, EditUserRq editUserReq);

	Iterable<UserRes> getAll();

	UserRes get(int id);

	OperationCompletionRes delete(int id);

	UserRes login(LoginReq loginReq);
}
