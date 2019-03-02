package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.UserConverter;
import com.finalhints.entity.Role;
import com.finalhints.entity.User;
import com.finalhints.handler.IUserRequestHandler;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.user.CreateUserRq;
import com.finalhints.request.user.EditUserRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.UserRes;

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
	public Iterable<UserRes> getAll() {
		Iterable<User> userEntities = userRepository.findAll();
		List<UserRes> userRes = new ArrayList<>();
		Iterator<User> iterator = userEntities.iterator();
		while (iterator.hasNext()) {
			userRes.add(UserConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return userRes;
	}

	@Override
	public UserRes get(int id) {
		Optional<User> user = userRepository.findById(id);
		return UserConverter.ENTITY_TO_RES.apply(user.get());
	}

	@Override
	public OperationCompletionRes delete(int id) {
		Optional<User> user = userRepository.findById(id);
		userRepository.delete(user.get());
		return new OperationCompletionRes("User with ID = '" + id + "' successfully deleted.");
	}

}
