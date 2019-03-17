import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TicketComponent } from './ticket.component';
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
    component: TicketComponent,
    data: {
      title: 'Ticket'
    },
    children:[
      {
        path: ':ticketId',
        loadChildren: './ticket-details/ticket-detail.module#TicketDetailModule',
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
export class TicketRoutingModule {}
