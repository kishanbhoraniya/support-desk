package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.entity.Status;
import com.finalhints.handler.IStatusRequestHandler;


@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	IStatusRequestHandler statusRequestHandler;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<Status> getAllStatus() {
		return statusRequestHandler.getAll();
	}
}
