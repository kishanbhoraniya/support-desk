package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Category;
import com.finalhints.request.category.CreateCategoryRq;
import com.finalhints.response.CategoryRes;

public final class CategoryConverter {

	public static final Function<CreateCategoryRq, Category> CREATE_TO_ENTITY = createCategoryReq -> {
		Category category = new Category();
		category.setName(createCategoryReq.getCategoryName());
		category.setCategoryDes(createCategoryReq.getCategoryDes());
		return category;
	};
	public static final Function<Category, CategoryRes> ENTITY_TO_RES = entity -> {
		CategoryRes res = new CategoryRes();
		res.setId(entity.getId());
		res.setName(entity.getName());
		res.setDesc(entity.getCategoryDes());
		return res;
	};

}
