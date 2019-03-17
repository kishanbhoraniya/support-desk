import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ChartsModule } from "ng2-charts/ng2-charts";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ButtonsModule } from "ngx-bootstrap/buttons";
import { CommonModule } from "@angular/common";
import { ModalModule } from "ngx-bootstrap/modal";
import { TicketDetailComponent } from "./ticket-detail.component";
import { TicketDetailRoutingModule } from "./ticket-detail-routing.module";
import { InputSwitchModule } from "primeng/inputswitch";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TicketDetailRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot(),
    InputSwitchModule
  ],
  declarations: [TicketDetailComponent]
})
export class TicketDetailModule {}
