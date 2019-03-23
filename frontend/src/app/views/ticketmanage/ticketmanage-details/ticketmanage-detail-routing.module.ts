import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TicketManageDetailComponent } from './ticketmanage-detail.component';

const routes: Routes = [
  {
    path: '',
    component: TicketManageDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketManageDetailRoutingModule {}
