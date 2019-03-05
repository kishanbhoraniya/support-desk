import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { getStyle, hexToRgba } from "@coreui/coreui/dist/js/coreui-utilities";
import { CustomTooltips } from "@coreui/coreui-plugin-chartjs-custom-tooltips";
import { UserService } from "./user.services";
import { BehaviorSubject, Observable } from "rxjs/index";
import { AppBreadcrumbService } from "../breadcrumb/breadcrumb.service";

@Component({
  templateUrl: "user.component.html"
})
export class UserComponent implements OnInit {
  userList = [];
  constructor(
    private userService: UserService,
    private breadcrumbService: AppBreadcrumbService
  ) {}

  ngOnInit(): void {
    this.initBreadCrumb();
    this.getAllUsers();
  }

  initBreadCrumb() {
    const breadcrumbs = [];
    breadcrumbs.push(
      {
        label: { title: "Home" },
        url: "/"
      },
      {
        label: { title: "Users" },
        url: "/user"
      }
    );
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }
  getAllUsers() {
    this.userService.getUsers().subscribe((response: any) => {
      this.userList = response;
    });
  }
}
