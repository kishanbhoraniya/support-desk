import { Component, OnInit, ViewChild } from "@angular/core";
import { FieldService } from "../../../../services/field.services";
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
  templateUrl: "category-detail.component.html"
})
export class CategoryDetailComponent implements OnInit {
  createFieldForm: FormGroup;
  projectId;
  categoryId;
  fields = [];

  @ViewChild("myModal")
  public myModal: ModalDirective;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fieldService: FieldService,
    private breadcrumbService: AppBreadcrumbService
  ) {}
  ngOnInit(): void {
    this.createFieldForm = new FormGroup({
      fieldName: new FormControl(null, [Validators.required]),
      fieldDes: new FormControl(null, []),
      fieldType: new FormControl(null, [Validators.required]),
      fieldReq: new FormControl(null, [Validators.required])
    });
    this.getAllField();
  }
  getAllField() {
    this.route.params.subscribe(params => {
      this.projectId = this.router.url.split("/")[2];
      this.categoryId = params["categoryId"];
      this.initBreadCrumb();
      this.fieldService
        .getFields(this.categoryId)
        .subscribe((response: any) => {
          this.fields = response.fields;
        });
    });
  }
  submit() {
    const field = {
      fieldName: this.createFieldForm.get("fieldName").value.trim(),
      fieldDes: this.createFieldForm.get("fieldDes").value.trim(),
      type: this.createFieldForm.get("fieldType").value.trim(),
      required: this.createFieldForm.get("fieldReq").value,
      categoryId: parseInt(this.categoryId, 10)
    };
    this.fieldService.createField(field).subscribe((response: any) => {
      this.myModal.hide();
      this.createFieldForm.reset();
      this.getAllField();
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
      },
      {
        label: { title: "Fields" },
        url: "/"
      }
    ];
    console.log(breadcrumbs);
    this.breadcrumbService.breadCrumbsSubject.next(
      Object.assign([], breadcrumbs)
    );
  }
}
