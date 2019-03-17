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
  cities2: SelectItem[];
  
  userId = JSON.parse(sessionStorage.getItem("user")).id
  constructor(
    private ticketService: TicketService,
    private authService: AuthService,
    private breadcrumbService: AppBreadcrumbService,
    private router: Router
    
  ) {}

  ngOnInit(): void {
    this.initBreadCrumb();
    this.createProjectForm = new FormGroup({
      projectName: new FormControl(null, [Validators.required]),
      projectDes: new FormControl(null, [])
    });
    this.cities2 = [
      {label:'Select City', value:null},
      {label:'New York', value:{id:1, name: 'New York', code: 'NY'}},
      {label:'Rome', value:{id:2, name: 'Rome', code: 'RM'}},
      {label:'London', value:{id:3, name: 'London', code: 'LDN'}},
      {label:'Istanbul', value:{id:4, name: 'Istanbul', code: 'IST'}},
      {label:'Paris', value:{id:5, name: 'Paris', code: 'PRS'}}
  ];
    this.getAllTickets();
  }

  getAllTickets() {

    this.ticketService.getTicketsById(this.userId).subscribe((response: any) => {
      this.TicketList = response;
    });
  }

  submit() {
    const project = {
      projectName: this.createProjectForm.get("projectName").value.trim(),
      projectDesc: this.createProjectForm.get("projectDes").value.trim(),
      adminUserId: JSON.parse(sessionStorage.getItem("user")).id
    };
    
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
