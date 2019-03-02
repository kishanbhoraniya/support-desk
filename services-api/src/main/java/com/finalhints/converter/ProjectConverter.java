package com.finalhints.converter;

import java.util.function.Function;

import com.finalhints.entity.Project;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.ProjectRes;

public final class ProjectConverter {

	public static final Function<CreateProjectRq, Project> CREATE_TO_ENTITY = createProjectReq -> {

		Project project = new Project();
		project.setName(createProjectReq.getProjectName());
		project.setDescription(createProjectReq.getProjectDesc());
		return project;
	};

	public static final Function<Project, ProjectRes> ENTITY_TO_RES = projectEntity -> {
		ProjectRes res = new ProjectRes();
		res.setId(projectEntity.getId());
		res.setName(projectEntity.getName());
		res.setDescription(projectEntity.getDescription());
		res.setAdminUserId(projectEntity.getAdmin().getId());
		res.setAdminName(projectEntity.getAdmin().getFirstName() + " " + projectEntity.getAdmin().getLastName());
		res.setCreated(projectEntity.getCreated());
		res.setUpdated(projectEntity.getUpdated());
		return res;
	};

}
