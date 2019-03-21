import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class ProjectService {
  constructor(private http: HttpClient) {}
  getProjects() {
    return this.http.get(environment.baseURL + "/project");
  }

  createProject(project) {
    return this.http.post(environment.baseURL + "/project", project, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }

  getUsers(projectId) {
    return this.http.get(
      environment.baseURL + "/project/" + projectId + "/user"
    );
  }
}
