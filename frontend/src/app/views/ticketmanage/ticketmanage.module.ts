import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { TicketManageComponent } from './ticketmanage.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { TicketManageRoutingModule } from './ticketmanage-routing.module';
import { CommonModule } from "@angular/common";
import { ModalModule } from 'ngx-bootstrap/modal';
import { DropdownModule } from 'primeng/dropdown';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TicketManageRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot(),
    DropdownModule
  ],
  declarations: [ TicketManageComponent,TicketListComponent,CreateTicketComponent]
})
export class TicketManageModule { }
