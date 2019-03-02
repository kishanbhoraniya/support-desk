package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.ProjectConverter;
import com.finalhints.entity.Project;
import com.finalhints.entity.User;
import com.finalhints.handler.IProjectRequestHandler;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.ProjectRes;

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

	@Override
	public ProjectRes get(int id) {
		Optional<Project> project = projectRepository.findById(id);
		ProjectRes res = ProjectConverter.ENTITY_TO_RES.apply(project.get());
		return res;
	}

	@Override
	public Iterable<ProjectRes> getAll() {
		Iterable<Project> projectEntities = projectRepository.findAll();
		List<ProjectRes> res = new ArrayList<>();
		Iterator<Project> iterator = projectEntities.iterator();
		while (iterator.hasNext()) {
			res.add(ProjectConverter.ENTITY_TO_RES.apply(iterator.next()));
		}
		return res;
	}

}
