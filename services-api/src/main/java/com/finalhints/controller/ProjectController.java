package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.IProjectRequestHandler;
import com.finalhints.request.project.CreateProjectRoleReq;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.ProjectRes;
import com.finalhints.response.UserRes;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	IProjectRequestHandler projectRequestHandler;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreatedRes createProject(@RequestBody @Validated CreateProjectRq createProjectRequest) {
		return projectRequestHandler.create(createProjectRequest);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{projectId}")
	public ProjectRes getProject(@PathVariable(value = "projectId") int id) {
		return projectRequestHandler.get(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<ProjectRes> getAllProjects() {
		return projectRequestHandler.getAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{projectId}/user")
	public Iterable<UserRes> getAllUsers(@PathVariable(value = "projectId") int projectId) {
		return projectRequestHandler.getAllUsers(projectId);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("{projectId}/user")
	public OperationCompletionRes addUserToProject(@PathVariable(value = "projectId") int projectId,
			@RequestBody CreateProjectRoleReq addUserToProjectReq) {
			System.out.println(addUserToProjectReq.getEmail());
			System.out.println(addUserToProjectReq.getRole());
		return projectRequestHandler.addUserToProject(projectId, addUserToProjectReq);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("{projectId}/user/{userId}")
	public OperationCompletionRes deleteUserToProject(@PathVariable(value = "projectId") int projectId,
			@PathVariable(value = "userId") int userId) {
		return projectRequestHandler.deleteUserToProject(projectId, userId);
	}
}
