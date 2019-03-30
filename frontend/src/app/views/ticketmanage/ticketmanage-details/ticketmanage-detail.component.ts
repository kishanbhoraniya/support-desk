import { Component, OnInit, ViewChild } from "@angular/core";
import { FieldService } from "../../../services/field.services";
import { TicketService } from "../../../services/ticket.services";
import { StatusService } from "../../../services/status.services";
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
import { SelectItem } from 'primeng/api';

@Component({
  templateUrl: "ticketmanage-detail.component.html"
})
export class TicketManageDetailComponent implements OnInit {
  setReplyForm: FormGroup;
  setStatusForm: FormGroup;
  ticketId;
  fields = [];
  reply=[];
  status=[];
  forstatus: SelectItem[];
  ticketdes;


  @ViewChild("setReply")
  public myModal: ModalDirective;

  @ViewChild("setStatus")
  public setStatusModal: ModalDirective;

  constructor(
    private ticketService: TicketService,
    private statusService: StatusService,
    private router: Router,
    private route: ActivatedRoute,
    private breadcrumbService: AppBreadcrumbService
  ) {}
  ngOnInit(): void {
    this.setReplyForm = new FormGroup({
      setticketreply: new FormControl(null, [Validators.required])
    });
    this.setStatusForm = new FormGroup({
      statusName: new FormControl(null, [Validators.required])
    });
    this.getAllField();
  }
  getAllField() {
    this.route.params.subscribe(params => {
      this.ticketId = params["ticketId"];
      this.initBreadCrumb();
      this.ticketService.getTicketDes(this.ticketId).subscribe((response: any) => {
          this.fields = response.fields;
          this.reply=response.reply;
          this.ticketdes = response;
        });
    });
  }

  getAllStatus(){
    
    this.statusService.getStatus().subscribe((response: any) => {
      this.status = response;
      this.forstatus = [];
      for (var i = 0; i < this.status.length; i++) {
        var p = this.status[i];
        this.forstatus.push({
          label: p["name"],
          value: p["id"]
        });
      }
      this.setStatusForm.get("statusName").setValue(this.forstatus[0].value);
    });
  }

  setTicketStatus(){
    const setstatus = {
      ticketId: this.ticketId,
      statusId: this.setStatusForm.get("statusName").value
    };
    this.ticketService.setTicketStatus(setstatus).subscribe((response: any) => {
      this.setStatusModal.hide();
      this.setStatusForm.reset();
      this.getAllField();
    });
  }

  showSetStatusPop(){
    this.getAllStatus();
    this.setStatusModal.show();
  }

  setTicketReply(){
    const reply = {
      ticketId: this.ticketId,
      reply: this.setReplyForm.get("setticketreply").value
    };
    this.ticketService.setTicketReply(reply).subscribe((response:any)=>{
      this.getAllField();
    });
    this.myModal.hide();
    
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
