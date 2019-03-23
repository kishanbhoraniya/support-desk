package com.finalhints.handler;


import com.finalhints.response.RoleRes;


public interface IRoleRequestHandler {
	Iterable<RoleRes> getAllRoles();
}
