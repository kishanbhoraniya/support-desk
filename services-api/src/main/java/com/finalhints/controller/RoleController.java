package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.entity.Role;
import com.finalhints.handler.IRoleRequestHandler;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.response.RoleRes;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	IRoleRequestHandler roleRequestHandler;

	// @RequestMapping(method = RequestMethod.GET)
	// public Role roleGet(@RequestParam(value = "id") int id) {
	// return roleRepository.findById(id).get();
	// }

	@RequestMapping(method = RequestMethod.POST)
	public void rolePost(@RequestBody Role role) {
		roleRepository.save(role);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping()
	public Iterable<RoleRes> getAllRoles() {
		return roleRequestHandler.getAllRoles();
	}
}
