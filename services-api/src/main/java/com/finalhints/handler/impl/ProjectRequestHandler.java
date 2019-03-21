package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.ProjectConverter;
import com.finalhints.entity.Project;
import com.finalhints.entity.ProjectRole;
import com.finalhints.entity.User;
import com.finalhints.handler.IProjectRequestHandler;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.ProjectRoleRepository;
import com.finalhints.reposioty.RoleRepository;
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

	@Autowired
	ProjectRoleRepository projectRoleRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public CreatedRes create(CreateProjectRq createProjectRequest) {
		Project project = ProjectConverter.CREATE_TO_ENTITY.apply(createProjectRequest);
		User adminUser = userRepository.findById(createProjectRequest.getAdminUserId()).get();
		project.setCreatedBy(adminUser);
		projectRepository.save(project);

		ProjectRole projectRole = new ProjectRole();
		projectRole.setProject(project);
		projectRole.setUser(adminUser);
		projectRole.setRole(roleRepository.findByName("admin").get());
		projectRoleRepository.save(projectRole);
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
			Project project = iterator.next();
			User admin = projectRoleRepository.getAdmin(project);

			ProjectRes projectRes = ProjectConverter.ENTITY_TO_RES.apply(project);
			projectRes.setAdminName(admin.getFirstName() + " " + admin.getLastName());
			projectRes.setAdminUserId(admin.getId());
			res.add(projectRes);
		}
		return res;
	}

}
