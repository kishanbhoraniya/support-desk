import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class CategoryService {
  constructor(private http: HttpClient) { }
  getCategories(projectId) {
    let params: HttpParams = new HttpParams();
    params = params.append('projectId', projectId);
    return this.http.get(environment.baseURL + "/category", { params: params });
  }
  createCategory(category) {
    return this.http.post(environment.baseURL + '/category', category, { headers: new HttpHeaders().set('Content-Type', 'application/json') });
  }
}
