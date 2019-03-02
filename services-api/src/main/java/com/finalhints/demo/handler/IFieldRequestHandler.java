package com.finalhints.demo.handler;

import com.finalhints.demo.request.field.CreateFieldRq;
import com.finalhints.demo.response.CreatedRes;

public interface IFieldRequestHandler {

	CreatedRes create(CreateFieldRq createFieldRequest);

}
