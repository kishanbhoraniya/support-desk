package com.finalhints.handler;

import com.finalhints.request.project.CreateProjectRoleReq;
import com.finalhints.request.project.CreateProjectRq;
import com.finalhints.response.CreatedRes;
import com.finalhints.response.OperationCompletionRes;
import com.finalhints.response.ProjectRes;
import com.finalhints.response.UserRes;

public interface IProjectRequestHandler {

	CreatedRes create(CreateProjectRq createProjectRequest);

	ProjectRes get(int id);

	Iterable<ProjectRes> getAll();

	Iterable<UserRes> getAllUsers(int projectId);

	OperationCompletionRes addUserToProject(int projectId, CreateProjectRoleReq addUserToProjectReq);

	OperationCompletionRes deleteUserToProject(int projectId, int userId);

}
