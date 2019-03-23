package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalhints.converter.RoleConverter;
import com.finalhints.converter.UserConverter;
import com.finalhints.entity.Role;
import com.finalhints.entity.User;
import com.finalhints.handler.IRoleRequestHandler;
import com.finalhints.handler.IUserRequestHandler;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.user.CreateUserRq;
import com.finalhints.request.user.EditUserRq;
import com.finalhints.request.user.LoginReq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.RoleRes;
import com.finalhints.response.UserRes;

@Service
public class RoleRequestHandler implements IRoleRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Iterable<RoleRes> getAllRoles() {
		Iterable<Role> roleEntities = roleRepository.findAll();
		List<RoleRes> roleRes = new ArrayList<>();
		Iterator<Role> iterator = roleEntities.iterator();
		while (iterator.hasNext()) {
			roleRes.add(RoleConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return roleRes;
		
	}

	

}
