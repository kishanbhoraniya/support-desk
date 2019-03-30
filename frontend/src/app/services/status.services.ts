import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class StatusService {
  getStatus() {
    return this.http.get(environment.baseURL + "/status");
  }
  constructor(private http: HttpClient) { }

}