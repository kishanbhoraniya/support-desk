package com.finalhints.demo.handler.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.demo.converter.FieldConverter;
import com.finalhints.demo.handler.IFieldRequestHandler;
import com.finalhints.demo.reposioty.CategoryRepository;
import com.finalhints.demo.reposioty.FieldRepository;
import com.finalhints.demo.reposioty.ProjectRepository;
import com.finalhints.demo.reposioty.UserRepository;
import com.finalhints.demo.request.field.CreateFieldRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.entity.Category;
import com.finalhints.entity.Field;

@Service
public class FieldRequestHandler implements IFieldRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FieldRepository fieldRepository;

	@Override
	public CreatedRes create(CreateFieldRq createFieldRequest) {
		Field field = FieldConverter.CREATE_TO_ENTITY.apply(createFieldRequest);
		Category category = categoryRepository.findById(createFieldRequest.getCategoryId()).get();
		field.setCategory(category);
		fieldRepository.save(field);
		return new CreatedRes(field.getId());
	}

}
