package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalhints.converter.RoleConverter;
import com.finalhints.entity.Role;
import com.finalhints.handler.IRoleRequestHandler;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.response.RoleRes;

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
