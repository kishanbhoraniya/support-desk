import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import {
  CommonModule,
  LocationStrategy,
  HashLocationStrategy
} from "@angular/common";
import { AuthGuard } from "./shared/auth-guard.service";
import { LoginGuard } from "./shared/login-guard.service";
import { RoleGuard } from "./shared/role-guard.service";
import { AuthService } from "./shared/auth.service";
import { HttpClientModule } from "@angular/common/http";
import { PerfectScrollbarModule } from "ngx-perfect-scrollbar";
import { PERFECT_SCROLLBAR_CONFIG } from "ngx-perfect-scrollbar";
import { PerfectScrollbarConfigInterface } from "ngx-perfect-scrollbar";
import { ProjectService } from "./services/project.services";
import { CategoryService } from "./services/category.services";
import { FieldService } from "./services/field.services";
import { TicketService } from "./services/ticket.services";
import { StatusService } from "./services/status.services";
import { RoleService } from "./services/role.services";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from "./app.component";

// Import containers
import { DefaultLayoutComponent } from "./containers";

import { LoginComponent } from "./views/login/login.component";
import { RegisterComponent } from "./views/register/register.component";
import { AppBreadcrumbModule } from "./views/breadcrumb/breadcrumb.module";
import { ToastModule } from "primeng/toast";


const APP_CONTAINERS = [DefaultLayoutComponent];

import {
  AppAsideModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule
} from "@coreui/angular";

// Import routing module
import { AppRoutingModule } from "./app.routing";

// Import 3rd party components
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TabsModule } from "ngx-bootstrap/tabs";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    BrowserAnimationsModule,
    ToastModule
  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    LoginComponent,
    RegisterComponent
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    AuthGuard,
    LoginGuard,
    RoleGuard,
    AuthService,
    ProjectService,
    CategoryService,
    FieldService,
    TicketService,
    StatusService,
    RoleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
