package com.finalhints.demo.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.demo.converter.ProjectConverter;
import com.finalhints.demo.handler.IProjectRequestHandler;
import com.finalhints.demo.reposioty.ProjectRepository;
import com.finalhints.demo.reposioty.UserRepository;
import com.finalhints.demo.request.project.CreateProjectRq;
import com.finalhints.demo.response.CreatedRes;
import com.finalhints.entity.Project;
import com.finalhints.entity.User;

@Service
public class ProjectRequestHandler implements IProjectRequestHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public CreatedRes create(CreateProjectRq createProjectRequest) {
		Project project = ProjectConverter.CREATE_TO_ENTITY.apply(createProjectRequest);
		User adminUser = userRepository.findById(createProjectRequest.getAdminUserId()).get();
		project.setAdmin(adminUser);
		project.setCreatedBy(adminUser);
		projectRepository.save(project);
		return new CreatedRes(project.getId());
	}

}
