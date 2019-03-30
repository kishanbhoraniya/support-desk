import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { TicketService } from "../../../services/ticket.services";
import { ProjectService } from "../../../services/project.services";
import { AuthService } from "../../../shared/auth.service";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import { SelectItem } from 'primeng/api';

@Component({
  templateUrl: "ticket-list.component.html"
})
export class TicketListComponent implements OnInit {
  assignUserForm: FormGroup;

  @ViewChild("assignUser")
  public myModal: ModalDirective;

  TicketList = [];
  users = [];
  forusers: SelectItem[];
  projectId;
  ticketId;
  

  userId = this.authService.getUser().id
  constructor(
    private ticketService: TicketService,
    private projectService: ProjectService,
    private authService: AuthService,
    private breadcrumbService: AppBreadcrumbService,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.assignUserForm = new FormGroup({
      userName: new FormControl(null, [Validators.required])
    });
    this.initBreadCrumb();
    this.getAllTickets();
  }

  getAllTickets() {

    this.ticketService.getTicketsForUser(this.userId).subscribe((response: any) => {
      this.TicketList = response;
    });
  }

  getAllUsers(){
    
    this.projectService.getUsers(this.projectId).subscribe((response: any) => {
      this.users = response;
      this.forusers = [];
      for (var i = 0; i < this.users.length; i++) {
        var p = this.users[i];
        this.forusers.push({
          label: p["firstName"]+" "+p["lastName"],
          value: p["id"]
        });
      }
      this.assignUserForm.get("userName").setValue(this.forusers[0].value);
    });
  }

  assignTicketToUser(){
    const assignuser = {
      ticketId: this.ticketId,
      userId: this.assignUserForm.get("userName").value
    };
    this.ticketService.assignTicket(assignuser).subscribe((response: any) => {
      this.myModal.hide();
      this.assignUserForm.reset();
      this.getAllTickets();
    });
  }

  showAssignUserPopup(event,projectId,ticketId){
    event.preventDefault();
    event.stopPropagation();
    console.log(event);
    this.projectId = projectId;
    this.ticketId = ticketId;
    this.getAllUsers();
    this.myModal.show();
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
