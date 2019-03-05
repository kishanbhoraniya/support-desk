import {
  Component,
  ElementRef,
  Inject,
  Input,
  OnDestroy,
  OnInit,
  Renderer2
} from "@angular/core";
import { DOCUMENT } from "@angular/common";

import { AppBreadcrumbService } from "./breadcrumb.service";

@Component({
  selector: "app-breadcrumb",
  templateUrl: "./breadcrumb.component.html"
})
export class BreadcrumbComponent implements OnInit, OnDestroy {
  @Input() fixed: boolean;

  public breadcrumbs;
  private readonly fixedClass = "breadcrumb-fixed";

  constructor(
    @Inject(DOCUMENT) private document: any,
    private renderer: Renderer2,
    public service: AppBreadcrumbService
  ) {}

  public ngOnInit(): void {
    this.isFixed(this.fixed);
    this.breadcrumbs = this.service.breadcrumbs;
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, this.fixedClass);
  }

  isFixed(fixed: boolean = this.fixed): void {
    if (fixed) {
      this.renderer.addClass(this.document.body, this.fixedClass);
    }
  }
}
