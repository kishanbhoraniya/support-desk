import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ChartsModule } from "ng2-charts/ng2-charts";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ButtonsModule } from "ngx-bootstrap/buttons";
import { CommonModule } from "@angular/common";
import { ModalModule } from "ngx-bootstrap/modal";
import { TicketManageDetailComponent } from "./ticketmanage-detail.component"
import { TicketManageDetailRoutingModule } from "./ticketmanage-detail-routing.module";
import { InputSwitchModule } from "primeng/inputswitch";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TicketManageDetailRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot(),
    InputSwitchModule
  ],
  declarations: [TicketManageDetailComponent]
})
export class TicketManageDetailModule {}
