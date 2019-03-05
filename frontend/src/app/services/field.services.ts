import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class FieldService {
  constructor(private http: HttpClient) { }
  getFields(categoryId) {
    return this.http.get(environment.baseURL + "/category/"+categoryId);
  }
  createField(field) {
    return this.http.post(environment.baseURL + '/field', field, { headers: new HttpHeaders().set('Content-Type', 'application/json') });
  }
}
