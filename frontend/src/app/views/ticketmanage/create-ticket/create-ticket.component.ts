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
import { SelectItem } from "primeng/api";
import { ProjectService } from "../../../services/project.services";
import { CategoryService } from "../../../services/category.services";
import { FieldService } from "../../../services/field.services";
import { MessageService } from "primeng/api";

@Component({
  templateUrl: "create-ticket.component.html"
})
export class CreateTicketComponent implements OnInit {
  createTicketForm: FormGroup;
  forprojects: SelectItem[];
  forcategory: SelectItem[];
  fields: [];
  projects: [];
  selectedProject;
  selectedCategory;
  categories: [];
  @ViewChild("myModal")
  public myModal: ModalDirective;

  constructor(
    private router: Router,
    private messageService: MessageService,
    private ticketService: TicketService,
    private authService: AuthService,
    private projectService: ProjectService,
    private categoryService: CategoryService,
    private breadcrumbService: AppBreadcrumbService,
    private fieldService: FieldService
  ) {}

  ngOnInit(): void {
    this.initBreadCrumb();
    this.createTicketForm = new FormGroup({
      projectName: new FormControl(null, [Validators.required]),
      categoryName: new FormControl(null, [Validators.required])
    });
    this.forprojects = [];
    this.getAllProjects();
  }

  getAllProjects() {
    this.projectService.getProjects().subscribe((response: any) => {
      this.projects = response;
      this.forprojects = [];
      for (var i = 0; i < this.projects.length; i++) {
        var p = this.projects[i];
        this.forprojects.push({
          label: p["name"],
          value: { id: p["id"], name: p["name"] }
        });
      }
      if (this.forprojects.length > 0) {
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
      },
      {
        label: { title: "Create Ticket" },
        url: "#"
      }
    );
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }

  onProjectChange() {
    this.selectedProject = this.createTicketForm.get("projectName").value;
    this.getCategory();
  }

  onCategoryChange() {
    this.selectedCategory = this.createTicketForm.get("categoryName").value;
    this.getCategory();
  }

  getCategory() {
    this.categoryService
      .getCategories(this.selectedProject["id"])
      .subscribe((response: any) => {
        this.categories = response;
        this.forcategory = [];
        for (var i = 0; i < this.categories.length; i++) {
          var p = this.categories[i];
          this.forcategory.push({
            label: p["name"],
            value: { id: p["id"], name: p["name"] }
          });
        }
        if (this.forcategory.length > 0) {
          this.selectedCategory = this.forcategory[0].value;
          this.getFields();
        }
      });
  }

  getFields() {
    this.fieldService
      .getFields(this.selectedCategory["id"])
      .subscribe((response: any) => {
        console.log(response);

        var controlsToRemove = [];
        Object.keys(this.createTicketForm.controls).forEach((key: string) => {
          if (key != "projectName" && key != "categoryName") {
            controlsToRemove.push(key);
          }
        });
        controlsToRemove.forEach(control => {
          this.createTicketForm.removeControl(control);
        });
        response.fields.forEach((field: any) => {
          var newControl = new FormControl(null, [Validators.required]);
          var name = "field_" + field["id"];
          this.createTicketForm.addControl(name, newControl);
        });
        this.fields = response.fields;
      });
  }
  submit() {
    var ticket = {
      categoryId: this.selectedCategory["id"],
      userId: JSON.parse(sessionStorage.getItem("user")).id,
      fieldMap: {}
    };
    this.fields.forEach(field => {
      var key: string = field["id"];
      ticket.fieldMap[key] = this.createTicketForm.get(
        "field_" + field["id"]
      ).value;
    });
    this.ticketService.create(ticket).subscribe((response: Response) => {
      this.messageService.add({
        severity: "success",
        summary: "Ticket Raised"
      });
      this.router.navigate(["/ticket"]);
    });
  }
}
