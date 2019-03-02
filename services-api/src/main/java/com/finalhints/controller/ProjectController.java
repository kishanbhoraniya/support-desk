package com.finalhints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finalhints.handler.IProjectRequestHandler;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.ProjectRes;

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
	public ProjectRes getProject(@RequestParam(value = "projectId") int id) {
		return projectRequestHandler.get(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Iterable<ProjectRes> getAllProjects() {
		return projectRequestHandler.getAll();
	}
}
