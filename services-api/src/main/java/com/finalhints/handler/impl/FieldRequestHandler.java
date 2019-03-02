package com.finalhints.handler.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.FieldConverter;
import com.finalhints.entity.Category;
import com.finalhints.entity.Field;
import com.finalhints.handler.IFieldRequestHandler;
import com.finalhints.reposioty.CategoryRepository;
import com.finalhints.reposioty.FieldRepository;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.field.CreateFieldRq;
import com.finalhints.response.CreatedRes;

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
