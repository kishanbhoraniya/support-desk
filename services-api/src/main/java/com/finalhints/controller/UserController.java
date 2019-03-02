package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.IUserRequestHandler;
import com.finalhints.request.user.CreateUserRq;
import com.finalhints.request.user.EditUserRq;
import com.finalhints.request.user.LoginReq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.UserRes;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserRequestHandler userRequestHandler;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreatedRes createUser(@RequestBody @Validated CreateUserRq createUserRequest) {
		return userRequestHandler.create(createUserRequest);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public CreatedRes editUser(@PathVariable(value = "id") int id, @RequestBody @Validated EditUserRq editUserReq) {
		return userRequestHandler.edit(id, editUserReq);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public OperationCompletionRes deleteUser(@PathVariable(value = "id") int id) {
		return userRequestHandler.delete(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public UserRes getUser(@PathVariable(value = "id") int id) {
		return userRequestHandler.get(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<UserRes> getAllUser() {
		return userRequestHandler.getAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/auth")
	public UserRes getUser(@RequestBody @Validated LoginReq loginReq) {
		return userRequestHandler.login(loginReq);
	}

}
