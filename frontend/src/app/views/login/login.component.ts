import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import { AuthService } from "../../shared/auth.service";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";

@Component({
  selector: "app-dashboard",
  templateUrl: "login.component.html"
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required])
    });
  }
  submit() {
    const user = {
      email: this.loginForm.get("email").value.trim(),
      password: this.loginForm.get("password").value.trim()
    };
    this.authService.loginUser(user).subscribe(
      (response: any) => {
        this.messageService.add({
          severity: "success",
          summary: "Login Successful"
        });
        sessionStorage.setItem("user", JSON.stringify(response));
        this.authService.loggedIn = true;
        if (this.authService.hasRole('admin') || this.authService.hasRole('manager')) {
          this.router.navigate(["/dashboard"]);
        } else if (this.authService.hasRole('agent')) {
          this.router.navigate(["/dashboard"]);
        } else {
          this.router.navigate(["/ticket"]);
        }
      },
      error => {
        this.messageService.add({
          severity: "error",
          summary: "Invalid Credentials"
        });
      }
    );
  }
}
