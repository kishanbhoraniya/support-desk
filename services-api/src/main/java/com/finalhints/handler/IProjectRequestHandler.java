package com.finalhints.handler;

import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.ProjectRes;

public interface IProjectRequestHandler {

	CreatedRes create(CreateProjectRq createProjectRequest);

	ProjectRes get(int id);

	Iterable<ProjectRes> getAll();

}
