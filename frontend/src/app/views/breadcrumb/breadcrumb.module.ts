import { CommonModule } from "@angular/common";
import { NgModule, ModuleWithProviders } from "@angular/core";
import { RouterModule } from "@angular/router";

// App Breadcrumb Component
import { AppBreadcrumbService } from "./breadcrumb.service";
import { BreadcrumbComponent } from "./breadcrumb.component";

// @dynamic
@NgModule({
  imports: [CommonModule, RouterModule],
  exports: [BreadcrumbComponent],
  declarations: [BreadcrumbComponent]
})
export class AppBreadcrumbModule {
  static forRoot(config?: any): ModuleWithProviders {
    return {
      ngModule: AppBreadcrumbModule,
      providers: [AppBreadcrumbService]
    };
  }
}
