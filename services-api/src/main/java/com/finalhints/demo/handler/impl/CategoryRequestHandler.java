package com.finalhints.demo.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.demo.converter.CategoryConverter;
import com.finalhints.demo.handler.ICategoryRequestHandler;
import com.finalhints.demo.reposioty.CategoryRepository;
import com.finalhints.demo.reposioty.ProjectRepository;
import com.finalhints.demo.reposioty.UserRepository;
import com.finalhints.demo.request.category.CreateCategoryRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.entity.Category;
import com.finalhints.entity.Project;


@Service
public class CategoryRequestHandler implements ICategoryRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public CreatedRes create(CreateCategoryRq createCategoryRequest) {
		Category category = CategoryConverter.CREATE_TO_ENTITY.apply(createCategoryRequest);
		Project project = projectRepository.findById(createCategoryRequest.getProjectId()).get();
		category.setProject(project);
		categoryRepository.save(category);
		
		return new CreatedRes(category.getId());
	}

}
