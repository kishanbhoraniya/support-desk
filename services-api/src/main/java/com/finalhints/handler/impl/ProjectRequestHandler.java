package com.finalhints.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalhints.converter.ProjectConverter;
import com.finalhints.converter.UserConverter;
import com.finalhints.entity.Project;
import com.finalhints.entity.ProjectRole;
import com.finalhints.entity.Role;
import com.finalhints.entity.User;
import com.finalhints.handler.IProjectRequestHandler;
import com.finalhints.reposioty.ProjectRepository;
import com.finalhints.reposioty.ProjectRoleRepository;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.UserRepository;
import com.finalhints.request.project.CreateProjectRoleReq;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.ProjectRes;
import com.finalhints.response.UserRes;

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

	@Override
	public Iterable<UserRes> getAllUsers(int projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		List<ProjectRole> projectRoles = projectRoleRepository.findByProject(project.get());
		List<UserRes> userRes = new ArrayList<UserRes>();
		for (ProjectRole role : projectRoles) {
			UserRes user = UserConverter.ENTITY_TO_RES.apply(role.getUser());
			user.setRole(role.getRole().getName());
			userRes.add(user);
		}
		return userRes;
	}

	@Override
	public OperationCompletionRes addUserToProject(int projectId, CreateProjectRoleReq addUserToProjectReq) {
		Project project = projectRepository.findById(projectId).get();
		User user = userRepository.findByEmail(addUserToProjectReq.getEmail()).get();
		Role role = roleRepository.findByName(addUserToProjectReq.getRole()).get();
		ProjectRole projectRole = new ProjectRole();
		projectRole.setRole(role);
		projectRole.setProject(project);
		projectRole.setUser(user);
		projectRoleRepository.save(projectRole);
		return new OperationCompletionRes("User added to Project");
	}

	@Override
	public OperationCompletionRes deleteUserToProject(int projectId, int userId) {
		Project project = projectRepository.findById(projectId).get();
		User user = userRepository.findById(userId).get();
		Optional<ProjectRole> role = projectRoleRepository.findByProjectAndUser(project, user);
		if (role.isPresent()) {
			projectRoleRepository.delete(role.get());
		}
		return new OperationCompletionRes("User removed from Project");
	}

}
