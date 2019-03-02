import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { getStyle, hexToRgba } from "@coreui/coreui/dist/js/coreui-utilities";
import { CustomTooltips } from "@coreui/coreui-plugin-chartjs-custom-tooltips";
import { UserService } from "./user.services";

@Component({
  templateUrl: "user.component.html"
})
export class UserComponent implements OnInit {
  userList = [];
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getUsers().subscribe((response: any) => {
      this.userList = response;
    });
  }
}
