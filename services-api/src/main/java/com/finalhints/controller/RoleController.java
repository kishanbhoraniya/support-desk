package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.entity.Role;
import com.finalhints.reposioty.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Role roleGet(@RequestParam(value = "id") int id) {
		return roleRepository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void rolePost(@RequestBody Role role) {
		roleRepository.save(role);
	}
}
