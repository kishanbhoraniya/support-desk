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
  constructor(private http: HttpClient) { }
  getTickets() {
    return this.http.get(environment.baseURL + "/ticket");
  }
  getTicketsByCreatedUserId(userId) {
    let params: HttpParams = new HttpParams();
    params = params.append("createdBy", userId);
    return this.http.get(environment.baseURL + "/ticket", { params: params });
  }

  getTicketsForUser(userId) {
    let params: HttpParams = new HttpParams();
    params = params.append("currentUser", userId);
    return this.http.get(environment.baseURL + "/ticket", { params: params });
  }

  getTicketDes(ticketId) {
    return this.http.get(environment.baseURL + "/ticket/" + ticketId);
  }

  assignTicket(assignuser){
    return this.http.post(environment.baseURL + "/ticket/assign",assignuser, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }

  setTicketStatus(setstatus){
    return this.http.post(environment.baseURL + "/ticket/status",setstatus, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }

  setTicketReply(reply){
    return this.http.post(environment.baseURL + "/ticket/reply",reply, {
      headers: new HttpHeaders().set("Content-Type", "application/json")
    });
  }
}
