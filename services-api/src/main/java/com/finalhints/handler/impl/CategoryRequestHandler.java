package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.CategoryConverter;
import com.finalhints.converter.FieldConverter;
import com.finalhints.entity.Category;
import com.finalhints.entity.Field;
import com.finalhints.entity.Project;
import com.finalhints.handler.ICategoryRequestHandler;
import com.finalhints.reposioty.CategoryRepository;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.request.category.CreateCategoryRq;
import com.finalhints.response.CategoryRes;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.FieldRes;

@Service
public class CategoryRequestHandler implements ICategoryRequestHandler {

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

	@Override
	public CategoryRes get(int categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		CategoryRes res = CategoryConverter.ENTITY_TO_RES.apply(category);
		List<FieldRes> fieldRes = new ArrayList<>();
		List<Field> fields = category.getCategoryFields();
		Iterator<Field> iterator = fields.iterator();
		while (iterator.hasNext()) {
			fieldRes.add(FieldConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		res.setFields(fieldRes);
		return res;
	}

	@Override
	public Iterable<CategoryRes> getAll(int projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		List<Category> categories = project.get().getProjectsCategories();
		List<CategoryRes> res = new ArrayList<>();
		Iterator<Category> iterator = categories.iterator();
		while (iterator.hasNext()) {
			res.add(CategoryConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return res;
	}

}
