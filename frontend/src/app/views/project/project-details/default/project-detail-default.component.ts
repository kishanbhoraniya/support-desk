import { Component, OnInit, ViewChild } from "@angular/core";
import { CategoryService } from "../../../../services/category.services";
import { ProjectService } from "../../../../services/project.services";
import { RoleService } from "../../../../services/role.services";
import { ActivatedRoute } from "@angular/router";
import { Router } from "@angular/router";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../../breadcrumb/breadcrumb.service";
import { TitleCasePipe } from '@angular/common'; 
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import { SelectItem } from "primeng/api";

@Component({
  templateUrl: "project-detail-default.component.html",
  providers:[TitleCasePipe]
})
export class ProjectDetailDefaultComponent implements OnInit {
  createCategoryForm: FormGroup;
  addUserForm: FormGroup;
  projectId;
  categories = [];
  users = [];
  roles = [];
  forroles: SelectItem[];
  deleteUserId;

  @ViewChild("myModal")
  public myModal: ModalDirective;

  @ViewChild("addUser")
  public addUser: ModalDirective;

  @ViewChild("removeUser")
  public removeUser: ModalDirective;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private projectService: ProjectService,
    private roleService: RoleService,
    private breadcrumbService: AppBreadcrumbService,
    private titlecasePipe:TitleCasePipe
  ) {}
  ngOnInit(): void {
    this.createCategoryForm = new FormGroup({
      categoryName: new FormControl(null, [Validators.required]),
      categoryDes: new FormControl(null, [])
    });
    this.addUserForm = new FormGroup({
      emailAddress: new FormControl(null, [Validators.required]),
      roleName: new FormControl(null, [Validators.required])
    });
    this.forroles =[]
    this.getAllCategory();
    this.getAllUsers();
    this.getAllRoles();

  }
  getAllCategory() {
    this.route.params.subscribe(params => {
      this.projectId = params["projectId"];
      this.initBreadCrumb();
      this.categoryService
        .getCategories(this.projectId)
        .subscribe((response: any) => {
          this.categories = response;
        });
    });
  }

  getAllRoles(){
    this.roleService.getRoles().subscribe((response: any) => {
      this.roles = response;
      this.forroles = [];
      for (var i = 0; i < this.roles.length; i++) {
        var p = this.roles[i];
        this.forroles.push({
          label: this.titlecasePipe.transform(p["roleName"]),
          value: p["roleName"]
        });
      }
      this.addUserForm.get("roleName").setValue(this.forroles[0].value);
    });
  }
  submit() {
    const category = {
      categoryName: this.createCategoryForm.get("categoryName").value.trim(),
      categoryDes: this.createCategoryForm.get("categoryDes").value.trim(),
      projectId: parseInt(this.projectId, 10)
    };
    this.categoryService.createCategory(category).subscribe((response: any) => {
      this.myModal.hide();
      this.createCategoryForm.reset();
      this.getAllCategory();
    });
  }

  submitUser() {
    const role = {
      email: this.addUserForm.get("emailAddress").value.trim(),
      role: this.addUserForm.get("roleName").value
    };
    this.roleService.setProjectRole(this.projectId,role).subscribe((response: any) => {
      this.addUser.hide();
      this.addUserForm.reset();
      this.getAllCategory();
      this.getAllUsers();
      this.getAllRoles();
    });
  }

  showConfirmDeletePopup(userId){
    this.deleteUserId = userId;
    this.removeUser.show();
  }
  deleteUser(){
    this.roleService.removeProjectRole(this.projectId,this.deleteUserId).subscribe((response: any)=>{
      this.removeUser.hide();
      this.getAllCategory();
      this.getAllUsers();
      this.getAllRoles();
    });
  }

  initBreadCrumb() {
    const breadcrumbs = [
      {
        label: { title: "Home" },
        url: "/"
      },
      {
        label: { title: "Project" },
        url: "/project"
      },
      {
        label: { title: this.projectId },
        url: "/project/" + this.projectId
      }
    ];
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }

  getAllUsers() {
    this.route.params.subscribe(params => {
      this.projectId = params["projectId"];
      this.initBreadCrumb();
      this.projectService
        .getUsers(this.projectId)
        .subscribe((response: any) => {
          this.users = response;
        });
    });
  }
}
