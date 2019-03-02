import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment.prod";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}
  getUsers() {
    return this.http.get(environment.baseURL + "/user");
  }
}
