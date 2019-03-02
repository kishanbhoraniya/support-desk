package com.finalhints.handler;

import com.finalhints.request.field.CreateFieldRq;
import com.finalhints.response.CreatedRes;

public interface IFieldRequestHandler {

	CreatedRes create(CreateFieldRq createFieldRequest);

}
