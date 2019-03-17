import { Component, OnInit, ViewChild } from "@angular/core";
import { FieldService } from "../../../services/field.services";
import { TicketService } from "../../../services/ticket.services";
import { ActivatedRoute } from "@angular/router";
import { Router } from "@angular/router";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";

@Component({
  templateUrl: "ticket-detail.component.html"
})
export class TicketDetailComponent implements OnInit {
  createFieldForm: FormGroup;
  ticketId;
  fields = [];
  ticketdes;


  @ViewChild("myModal")
  public myModal: ModalDirective;

  constructor(
    private ticketService: TicketService,
    private router: Router,
    private route: ActivatedRoute,
    private breadcrumbService: AppBreadcrumbService
  ) {}
  ngOnInit(): void {
    this.getAllField();
  }
  getAllField() {
    this.route.params.subscribe(params => {
      this.ticketId = params["ticketId"];
      this.initBreadCrumb();
      this.ticketService.getTicketDes(this.ticketId).subscribe((response: any) => {
          this.fields = response.fields;
          this.ticketdes = response;
        });
    });
  }

  initBreadCrumb() {
    const breadcrumbs = [
      {
        label: { title: "Home" },
        url: "/"
      },
      {
        label: { title: "Ticket" },
        url: "/ticket"
      },
      {
        label: { title: this.ticketId },
        url: "/ticket/" + this.ticketId
      }
    ];
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }
}
