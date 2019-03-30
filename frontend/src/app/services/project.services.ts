import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class ProjectService {
  constructor(private http: HttpClient) { }

  getAllProjects() {
    return this.http.get(environment.baseURL + "/project");
  }

  getProjects(userId: any) {
    let params: HttpParams = new HttpParams();
    if (userId) {
      params = params.append("currentUser", userId);
    }
    return this.http.get(environment.baseURL + "/project", { params: params });
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
