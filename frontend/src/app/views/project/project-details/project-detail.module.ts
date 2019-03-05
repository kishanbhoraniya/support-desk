import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { ProjectDetailDefaultComponent } from './default/project-detail-default.component';
import { ProjectDetailComponent } from './project-detail.component';
import { ProjectDetailRoutingModule } from './project-detail-routing.module';
import {
  CommonModule,
  LocationStrategy,
  HashLocationStrategy
} from "@angular/common";
import { ModalModule } from 'ngx-bootstrap/modal';
import { from } from 'rxjs';
@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    ProjectDetailRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot()
  ],
  providers: [],
  declarations: [ ProjectDetailComponent,ProjectDetailDefaultComponent]
})
export class ProjectDetailModule { }
