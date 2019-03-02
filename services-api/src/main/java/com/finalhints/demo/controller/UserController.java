package com.finalhints.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.demo.handler.IUserRequestHandler;
import com.finalhints.demo.request.user.CreateUserRq;
import com.finalhints.demo.request.user.EditUserRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.demo.response.user.UserRes;

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
	@PutMapping
	public CreatedRes editUser(@RequestParam(value = "id") int id, @RequestBody @Validated EditUserRq editUserReq) {
		return userRequestHandler.edit(id, editUserReq);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public CreatedRes deleteUser(@RequestParam(value = "id") int id) {
		return userRequestHandler.delete(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public UserRes getUser(@RequestParam(value = "id") int id) {
		return userRequestHandler.get(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<UserRes> getAllUser() {
		return userRequestHandler.getAll();
	}

}
