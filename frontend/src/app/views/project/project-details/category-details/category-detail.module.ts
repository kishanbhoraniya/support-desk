import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ChartsModule } from "ng2-charts/ng2-charts";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ButtonsModule } from "ngx-bootstrap/buttons";
import { CommonModule } from "@angular/common";
import { ModalModule } from "ngx-bootstrap/modal";
import { CategoryDetailComponent } from "./category-detail.component";
import { CategoryDetailRoutingModule } from "./category-detail-routing.module";
import { InputSwitchModule } from "primeng/inputswitch";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CategoryDetailRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    ModalModule.forRoot(),
    InputSwitchModule
  ],
  declarations: [CategoryDetailComponent]
})
export class CategoryDetailModule {}
