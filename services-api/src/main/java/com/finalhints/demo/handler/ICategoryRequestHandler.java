package com.finalhints.demo.handler;

import com.finalhints.demo.request.category.CreateCategoryRq;
import com.finalhints.demo.response.CreatedRes;

public interface ICategoryRequestHandler {

	CreatedRes create(CreateCategoryRq createCategoryRequest);

}
