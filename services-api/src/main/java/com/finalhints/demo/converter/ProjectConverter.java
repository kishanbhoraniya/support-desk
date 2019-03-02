package com.finalhints.demo.converter;

import java.util.function.Function;

import com.finalhints.demo.request.project.CreateProjectRq;
import com.finalhints.entity.Project;

public final class ProjectConverter {

	public static final Function<CreateProjectRq, Project> CREATE_TO_ENTITY = createProjectReq -> {

		Project project = new Project();
		project.setName(createProjectReq.getProjectName());
		project.setDescription(createProjectReq.getProjectDesc());
		return project;
	};

}
