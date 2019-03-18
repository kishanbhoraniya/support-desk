import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from "../../environments/environment.prod";

@Injectable()
export class TicketService {
  create(ticket) {
    return this.http.post(environment.baseURL + "/ticket", ticket, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }
  constructor(private http: HttpClient) {}
  getTickets() {
    return this.http.get(environment.baseURL + "/ticket");
  }
  getTicketsById(userId) {
    let params: HttpParams = new HttpParams();
    params = params.append("user", userId);
    return this.http.get(environment.baseURL + "/ticket", { params: params });
  }
  getTicketDes(ticketId) {
    return this.http.get(environment.baseURL + "/ticket/" + ticketId);
  }
}
