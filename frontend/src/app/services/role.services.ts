import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class RoleService {
  
  constructor(private http: HttpClient) {}
  getRoles() {
    return this.http.get(environment.baseURL + "/role");
  }
  setProjectRole(projectId,role) {
    return this.http.post(environment.baseURL + "/project/"+projectId+"/user", role, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }

  removeProjectRole(projectId,userId){
    return this.http.delete(environment.baseURL + "/project/"+projectId+"/user/"+userId);
  
  }
}
