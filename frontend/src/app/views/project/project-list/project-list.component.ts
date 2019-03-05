import { Component, OnInit,ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from "../../../services/project.services";
import { AuthService } from "../../../shared/auth.service";
import { ModalDirective } from 'ngx-bootstrap/modal';
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";

@Component({
  templateUrl: 'project-list.component.html'
})
export class ProjectListComponent implements OnInit{
  createProjectForm: FormGroup;
  
  @ViewChild('myModal')
  public myModal: ModalDirective;
  
  projectList = [];
  constructor(private projectService: ProjectService,private authService: AuthService) {}

  ngOnInit(): void {
    this.createProjectForm = new FormGroup({
      projectName: new FormControl(null, [Validators.required]),
      projectDes: new FormControl(null, [])
    });
    this.getAllProjects();
  }

  getAllProjects() {
    this.projectService.getProjects().subscribe((response: any) => {
      this.projectList = response;
    });
  }

  submit() {
    const project = {
      projectName: this.createProjectForm.get("projectName").value.trim(),
      projectDesc: this.createProjectForm.get("projectDes").value.trim(),
      adminUserId:JSON.parse(sessionStorage.getItem('user')).id
    };
    this.projectService.createProject(project).subscribe((response: any) => {
      this.myModal.hide();
      this.createProjectForm.reset();
      this.getAllProjects();
    });
  }
}