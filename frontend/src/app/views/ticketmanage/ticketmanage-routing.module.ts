import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TicketManageComponent } from './ticketmanage.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { from } from 'rxjs';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';

const routes: Routes = [
  {
    path:'createticket',
    component: CreateTicketComponent,
  },
  {
    path: '',
    component: TicketManageComponent,
    data: {
      title: 'Ticket Management'
    },
    children:[
      {
        path: ':ticketId',
        loadChildren: './ticketmanage-details/ticketmanage-detail.module#TicketManageDetailModule',
      },
      {
        path: '',
        pathMatch: 'full',
        component: TicketListComponent
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketManageRoutingModule {}
