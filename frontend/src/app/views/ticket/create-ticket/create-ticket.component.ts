import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { TicketService } from "../../../services/ticket.services";
import { AuthService } from "../../../shared/auth.service";
import { ModalDirective } from "ngx-bootstrap/modal";
import { AppBreadcrumbService } from "../../breadcrumb/breadcrumb.service";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import {SelectItem} from 'primeng/api';
import { ProjectService } from '../../../services/project.services';
import { CategoryService } from '../../../services/category.services';

@Component({
  templateUrl: "create-ticket.component.html"
})
export class CreateTicketComponent implements OnInit {
    createTicketForm: FormGroup;
    forprojects: SelectItem[];
    forcategory: SelectItem[];
    projects: [];
    selectedProject;
    categories: [];
  @ViewChild("myModal")
  public myModal: ModalDirective;

  constructor(
    private ticketService: TicketService,
    private authService: AuthService,
    private projectService: ProjectService,
    private categoryService: CategoryService,
    private breadcrumbService: AppBreadcrumbService,
    
    
  ) {}

  ngOnInit(): void {
    this.initBreadCrumb();
    this.createTicketForm = new FormGroup({
        projectName: new FormControl(null, [Validators.required]),
        categoryName: new FormControl(null, [Validators.required]),
        projectDes: new FormControl(null, [])
      });
      this.forprojects = [];
    this.getAllProjects();
  }

  getAllProjects() {
    this.projectService.getProjects().subscribe((response: any) => {
        this.projects = response;
        this.forprojects = [];
        for(var i=0;i<this.projects.length;i++){
            var p = this.projects[i];
            this.forprojects.push({label:p['name'], value:{id:p['id'], name: p['name']}})
        }
        if(this.forprojects.length>0){
            this.selectedProject = this.forprojects[0].value;
            this.getCategory();
        }
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
        label: { title: "Ticket" },
        url: "/ticket"
      }
    );
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }

  onProjectChange(){
    this.selectedProject = this.createTicketForm.get("projectName").value;
    this.getCategory();
  }
  getCategory(){
    this.categoryService.getCategories(this.selectedProject['id']).subscribe((response: any) => {
        this.categories = response;
        this.forcategory = [];
        for(var i=0;i<this.categories.length;i++){
            var p = this.categories[i];
            this.forcategory.push({label:p['name'], value:{id:p['id'], name: p['name']}})
        }
     });
    }
}
