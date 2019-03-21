import { Component, OnInit, ViewChild } from "@angular/core";
import { CategoryService } from "../../../../services/category.services";
import { ProjectService } from "../../../../services/project.services";
import { ActivatedRoute } from "@angular/router";
import { Router } from "@angular/router";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";

@Component({
  templateUrl: "project-detail-default.component.html"
})
export class ProjectDetailDefaultComponent implements OnInit {
  createCategoryForm: FormGroup;
  projectId;
  categories = [];
  users = [];

  @ViewChild("myModal")
  public myModal: ModalDirective;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private projectService: ProjectService,
    private breadcrumbService: AppBreadcrumbService
  ) {}
  ngOnInit(): void {
    this.createCategoryForm = new FormGroup({
      categoryName: new FormControl(null, [Validators.required]),
      categoryDes: new FormControl(null, [])
    });
    this.getAllCategory();
    this.getAllUsers();
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
