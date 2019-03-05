import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import {
  CommonModule,
  LocationStrategy,
  HashLocationStrategy
} from "@angular/common";
import { AuthGuard } from "./shared/auth-guard.service";
import { LoginGuard } from "./shared/login-guard.service";
import { AuthService } from "./shared/auth.service";
import { HttpClientModule } from "@angular/common/http";
import { PerfectScrollbarModule } from "ngx-perfect-scrollbar";
import { PERFECT_SCROLLBAR_CONFIG } from "ngx-perfect-scrollbar";
import { PerfectScrollbarConfigInterface } from "ngx-perfect-scrollbar";
import { ProjectService } from "./services/project.services"
import { CategoryService } from "./services/category.services"
import { FieldService } from "./services/field.services"

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from "./app.component";

// Import containers
import { DefaultLayoutComponent } from "./containers";

import { P404Component } from "./views/error/404.component";
import { P500Component } from "./views/error/500.component";
import { LoginComponent } from "./views/login/login.component";
import { RegisterComponent } from "./views/register/register.component";
import { AppBreadcrumbModule } from "./views/breadcrumb/breadcrumb.module";

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
import { ChartsModule } from "ng2-charts/ng2-charts";
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
    ChartsModule
  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
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
    AuthService,
    ProjectService,
    CategoryService,
    FieldService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
