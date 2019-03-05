import { Component, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from "../../../../services/category.services"
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";

@Component({
  templateUrl: 'project-detail-default.component.html'
})
export class ProjectDetailDefaultComponent implements OnInit {
  createCategoryForm: FormGroup;
  projectId;
  categories = [];

  @ViewChild('myModal')
  public myModal: ModalDirective;

  constructor(private route: ActivatedRoute, private categoryService: CategoryService) { }
  ngOnInit(): void {
    this.createCategoryForm = new FormGroup({
      categoryName: new FormControl(null, [Validators.required]),
      categoryDes: new FormControl(null, [])
    });
    this.getAllCategory();
  }
  getAllCategory() {
    this.route.params.subscribe(params => {
      this.projectId = params['projectId'];
      this.categoryService.getCategories(this.projectId).subscribe((response: any) => {
        this.categories = response;
      });
    });
  }
  submit() {
    const category = {
      categoryName: this.createCategoryForm.get("categoryName").value.trim(),
      categoryDes: this.createCategoryForm.get("categoryDes").value.trim(),
      projectId:parseInt(this.projectId,10)
    };
    this.categoryService.createCategory(category).subscribe((response: any) => {
      this.myModal.hide();
      this.createCategoryForm.reset();
      this.getAllCategory();
    });
  }
}
