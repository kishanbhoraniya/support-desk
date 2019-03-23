package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Role;
import com.finalhints.response.RoleRes;


public final class RoleConverter {

	public static final Function<Role, RoleRes> ENTITY_TO_RES = roleEntity -> {
		RoleRes res = new RoleRes();
		res.setRoleId(roleEntity.getId());
		res.setRoleName(roleEntity.getName());
		return res;
	};

}
