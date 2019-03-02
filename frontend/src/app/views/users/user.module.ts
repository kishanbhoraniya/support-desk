import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ChartsModule } from "ng2-charts/ng2-charts";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ButtonsModule } from "ngx-bootstrap/buttons";
import { UserComponent } from "./user.component";
import { UserService } from "./user.services";
import { CommonModule } from "@angular/common";
import { DashboardRoutingModule } from "./user-routing.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    DashboardRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot()
  ],
  providers: [UserService],
  declarations: [UserComponent]
})
export class UserModule {}
