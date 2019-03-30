package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalhints.converter.UserConverter;
import com.finalhints.entity.ProjectRole;
import com.finalhints.entity.Role;
import com.finalhints.entity.User;
import com.finalhints.handler.IUserRequestHandler;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.user.CreateUserRq;
import com.finalhints.request.user.EditUserRq;
import com.finalhints.request.user.LoginReq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.UserRes;

@Service
public class UserRequestHandler implements IUserRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public CreatedRes create(CreateUserRq createUserRequest) {
		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		User user = UserConverter.CREATE_TO_ENTITY.apply(createUserRequest);
		Optional<Role> role = roleRepository.findById(4);
		user.setRole(role.get());
		user.setActive(true);
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

	@Override
	public UserRes login(LoginReq loginReq) {
		Optional<User> user = userRepository.findByEmail(loginReq.getEmail());
		if (user.isPresent()) {
			if (passwordEncoder.matches(loginReq.getPassword(), user.get().getPassword())) {
				User userEntiry = user.get();
				UserRes userRes = UserConverter.ENTITY_TO_RES.apply(userEntiry);
				List<ProjectRole> projectRoles = userEntiry.getProjectRoles();
				Role role = userEntiry.getRole();
				for (ProjectRole projectRole : projectRoles) {
					if (projectRole.getRole().getId() < role.getId()) {
						role = projectRole.getRole();
					}
				}
				userRes.setRole(role.getName());
				return userRes;
			} else {
				throw new RuntimeException("Invalid Password");
			}
		} else {
			throw new RuntimeException("User not found");
		}
	}

}
