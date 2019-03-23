import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { TicketService } from "../../../services/ticket.services";
import { AuthService } from "../../../shared/auth.service";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import {SelectItem} from 'primeng/api';

@Component({
  templateUrl: "ticket-list.component.html"
})
export class TicketListComponent implements OnInit {
  createProjectForm: FormGroup;

  @ViewChild("myModal")
  public myModal: ModalDirective;

  TicketList = [];

  
  userId = JSON.parse(sessionStorage.getItem("user")).id
  constructor(
    private ticketService: TicketService,
    private authService: AuthService,
    private breadcrumbService: AppBreadcrumbService,
    private router: Router
    
  ) {}

  ngOnInit(): void {
    this.initBreadCrumb();
    this.getAllTickets();
  }

  getAllTickets() {

    this.ticketService.getTicketsById(this.userId).subscribe((response: any) => {
      this.TicketList = response;
    });
  }

  initBreadCrumb() {
    const breadcrumbs = [];
    breadcrumbs.push(
      {
        label: { title: "Home" },
        url: "/"
      },
      {
        label: { title: "Ticket" },
        url: "/ticket"
      }
    );
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }
}
