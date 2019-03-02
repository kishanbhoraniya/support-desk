package com.finalhints.demo.handler.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.demo.converter.UserConverter;
import com.finalhints.demo.handler.IUserRequestHandler;
import com.finalhints.demo.reposioty.RoleRepository;
import com.finalhints.demo.reposioty.UserRepository;
import com.finalhints.demo.request.user.CreateUserRq;
import com.finalhints.demo.request.user.EditUserRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.demo.response.user.UserRes;
import com.finalhints.entity.Role;
import com.finalhints.entity.User;

@Service
public class UserRequestHandler implements IUserRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public CreatedRes create(CreateUserRq createUserRequest) {
		User user = UserConverter.CREATE_TO_ENTITY.apply(createUserRequest);
		Optional<Role> role = roleRepository.findById(1);
		user.setRole(role.get());
		userRepository.save(user);
		CreatedRes res = new CreatedRes(user.getId());
		return res;
	}

	@Override
	public CreatedRes edit(int id, EditUserRq editUserReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRes> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRes get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreatedRes delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
