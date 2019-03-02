package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.IFieldRequestHandler;
import com.finalhints.request.field.CreateFieldRq;
import com.finalhints.response.CreatedRes;

@RestController
@RequestMapping("/field")
public class FieldController {

	@Autowired
	IFieldRequestHandler fieldRequestHandler;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreatedRes createProject(@RequestBody @Validated CreateFieldRq createFieldRequest) {
		return fieldRequestHandler.create(createFieldRequest);
	}

}
