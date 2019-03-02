package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Field;
import com.finalhints.request.field.CreateFieldRq;
import com.finalhints.response.FieldRes;

public final class FieldConverter {

	public static final Function<CreateFieldRq, Field> CREATE_TO_ENTITY = createFieldReq -> {

		Field field = new Field();
		field.setName(createFieldReq.getFieldName());
		field.setDes(createFieldReq.getFieldDes());
		field.setType(createFieldReq.getType());
		field.setRequired(createFieldReq.getRequired());
		return field;
	};

	public static final Function<Field, FieldRes> ENTITY_TO_RES = entity -> {
		FieldRes res = new FieldRes();
		res.setId(entity.getId());
		res.setName(entity.getName());
		res.setDesc(entity.getDes());
		res.setRequired(entity.getRequired());
		res.setType(entity.getType());
		return res;

	};
}
