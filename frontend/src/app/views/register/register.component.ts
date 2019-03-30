import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import { AuthService } from "../../shared/auth.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent implements OnInit {
  firstname;
  email;
  password;
  registerForm: FormGroup;
  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService 
  ) 
  {}  
  ngOnInit(): void {
    this.registerForm = new FormGroup({
      Firstname: new FormControl(null, [Validators.required]),
      Lastname: new FormControl(null, [Validators.required]),
      Email : new FormControl(null, [Validators.required]),
      Password: new FormControl(null, [Validators.required]),
      Number: new FormControl(null, [])
    });
  }
  submit() {
    this.firstname=this.registerForm.get("Firstname").value;
    this.email=this.registerForm.get("Firstname").value;
    this.password=this.registerForm.get("Firstname").value;
    
    if(this.firstname==null || this.email==null || this.password==null){
      this.messageService.add({
        severity: "error",
        summary: "Enter Details"
      })
    }
    else{
      const user = {
        firstName: this.registerForm.get("Firstname").value.trim(),
        lastName: this.registerForm.get("Lastname").value.trim(),
        email: this.registerForm.get("Email").value.trim(),
        password: this.registerForm.get("Password").value.trim(),
        number: this.registerForm.get("Number").value.trim(),
      };
      this.authService.registerUser(user).subscribe(
        (response: any) => {
          this.messageService.add({
            severity: "success",
            summary: "Registration Successful"
          });
          this.router.navigate(["/login"]);
        }
      );
    }
    
  }


}


