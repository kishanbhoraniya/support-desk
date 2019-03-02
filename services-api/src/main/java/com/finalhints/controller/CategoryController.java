package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.ICategoryRequestHandler;
import com.finalhints.request.category.CreateCategoryRq;
import com.finalhints.response.CategoryRes;
import com.finalhints.response.CreatedRes;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	ICategoryRequestHandler categoryRequestHandler;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreatedRes createCategory(@RequestBody @Validated CreateCategoryRq createCategoryRequest) {
		return categoryRequestHandler.create(createCategoryRequest);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{categoryId}")
	public CategoryRes getCategory(@PathVariable(value = "categoryId", required = true) int categoryId) {
		return categoryRequestHandler.get(categoryId);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<CategoryRes> getAllCategories(@RequestParam(value = "projectId", required = true) int projectId) {
		return categoryRequestHandler.getAll(projectId);
	}

}
