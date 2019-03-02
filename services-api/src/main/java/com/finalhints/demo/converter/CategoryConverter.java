package com.finalhints.demo.converter;

import java.util.function.Function;

import com.finalhints.demo.request.category.CreateCategoryRq;
import com.finalhints.entity.Category;

public final class CategoryConverter {

	public static final Function<CreateCategoryRq, Category> CREATE_TO_ENTITY = createCategoryReq -> {

		
		Category category = new Category();
		category.setName(createCategoryReq.getCategoryName());
		category.setCategoryDes(createCategoryReq.getCategoryDes());
		
		return category;
	};

}
