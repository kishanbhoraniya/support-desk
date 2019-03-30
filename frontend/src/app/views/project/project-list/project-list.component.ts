import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { ProjectService } from "../../../services/project.services";
import { AuthService } from "../../../shared/auth.service";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";

@Component({
  templateUrl: "project-list.component.html"
})
export class ProjectListComponent implements OnInit {
  createProjectForm: FormGroup;
  user;
  userrole;

  @ViewChild("myModal")
  public myModal: ModalDirective;

  projectList = [];
  constructor(

    private projectService: ProjectService,
    private authService: AuthService,
    private breadcrumbService: AppBreadcrumbService
  ) { }

  ngOnInit(): void {
    this.initBreadCrumb();
    this.user = this.authService.getUser();
    this.createProjectForm = new FormGroup({
      projectName: new FormControl(null, [Validators.required]),
      projectDes: new FormControl(null, [])
    });
    this.userrole = this.authService.getUser().role;
    this.getAllProjects(this.user.id);
  }

  getAllProjects(userId) {
    this.projectService.getProjects(userId).subscribe((response: any) => {
      this.projectList = response;
    });
  }

  submit() {
    const project = {
      projectName: this.createProjectForm.get("projectName").value.trim(),
      projectDesc: this.createProjectForm.get("projectDes").value.trim(),
      adminUserId: this.authService.getUser().id
    };
    this.projectService.createProject(project).subscribe((response: any) => {
      this.myModal.hide();
      this.createProjectForm.reset();
      this.getAllProjects(this.user.id);
    });
  }

  initBreadCrumb() {
    const breadcrumbs = [];
    breadcrumbs.push(
      {
        label: { title: "Home" },
        url: "/"
      },
      {
        label: { title: "Project" },
        url: "/project"
      }
    );
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }
}
