import { Component, OnInit, ViewChild } from '@angular/core';
import { FieldService } from "../../../../services/field.services"
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
  templateUrl: 'category-detail.component.html'
})
export class CategoryDetailComponent implements OnInit{
  createFieldForm: FormGroup;
  projectId;
  categoryId;
  fields = [];

  @ViewChild('myModal')
  public myModal: ModalDirective;

  constructor(private route: ActivatedRoute, private fieldService: FieldService) { }
  ngOnInit(): void {
    this.createFieldForm = new FormGroup({
      fieldName: new FormControl(null, [Validators.required]),
      fieldDes: new FormControl(null, []),
      fieldType : new FormControl(null, [Validators.required]),
      fieldReq : new FormControl(null, [Validators.required])
    });
    this.getAllField();
  }
  getAllField() {
    this.route.params.subscribe(params => {
      this.projectId = params['projectId'];
      this.categoryId = params['categoryId'];
      this.fieldService.getFields(this.categoryId).subscribe((response: any) => {
        this.fields = response.fields;
      });
    });
  }
  submit() {
    const field = {
      fieldName: this.createFieldForm.get("fieldName").value.trim(),
      fieldDes: this.createFieldForm.get("fieldDes").value.trim(),
      type: this.createFieldForm.get("fieldType").value.trim(),
      required: parseInt(this.createFieldForm.get("fieldReq").value.trim(),10),
      categoryId:parseInt(this.categoryId,10)
    };
    this.fieldService.createField(field).subscribe((response: any) => {
      this.myModal.hide();
      this.createFieldForm.reset();
      this.getAllField();
    });
  }

}
