package com.finalhints.demo.converter;

import java.util.function.Function;

import com.finalhints.demo.request.field.CreateFieldRq;
import com.finalhints.entity.Field;

public final class FieldConverter {

	public static final Function<CreateFieldRq, Field> CREATE_TO_ENTITY = createFieldReq -> {

		Field field = new Field();
		field.setName(createFieldReq.getFieldName());
		field.setDes(createFieldReq.getFieldDes());
		field.setType(createFieldReq.getType());
		field.setRequired(createFieldReq.getRequired());
		return field;
	};

}
