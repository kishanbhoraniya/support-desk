import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormControl,
  Validators,
  ValidationErrors
} from "@angular/forms";
import { AuthService } from "../../shared/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-dashboard",
  templateUrl: "login.component.html"
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(private authService: AuthService, private router: Router) {}

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
    this.authService.loginUser(user).subscribe((response: any) => {
      console.log(response);
      sessionStorage.setItem("user", JSON.stringify(response));
      this.authService.loggedIn = true;
      this.router.navigate(["/dashboard"]);
    });
  }
}
