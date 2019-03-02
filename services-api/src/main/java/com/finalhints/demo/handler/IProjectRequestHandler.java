package com.finalhints.demo.handler;

import com.finalhints.demo.request.project.CreateProjectRq;
import com.finalhints.demo.response.CreatedRes;

public interface IProjectRequestHandler {

	CreatedRes create(CreateProjectRq createProjectRequest);

}
