package com.finalhints.handler;

import com.finalhints.request.category.CreateCategoryRq;
import com.finalhints.response.CategoryRes;
import com.finalhints.response.CreatedRes;

public interface ICategoryRequestHandler {

	CreatedRes create(CreateCategoryRq createCategoryRequest);

	CategoryRes get(int categoryId);

	Iterable<CategoryRes> getAll(int projectId);

}
