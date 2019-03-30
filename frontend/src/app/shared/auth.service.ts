import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment.prod';

@Injectable()
export class AuthService {
    user: any;
    loggedIn = false;
    constructor(private http: HttpClient, private router: Router) {
        // Checking for the remember me option
        if (sessionStorage.getItem('user') !== null) {
            this.user = JSON.parse(sessionStorage.getItem('user'));
            this.loggedIn = true;
        } else if (localStorage.getItem('user') !== null) {
            this.user = JSON.parse(localStorage.getItem('user'));
            this.loggedIn = true;
        }
    }

    isAuthenticated() {
        return this.loggedIn;
    }

    loginUser(user) {
        return this.http.post(environment.baseURL + '/user/auth', user, { headers: new HttpHeaders().set('Content-Type', 'application/json') });
    }

    registerUser(user) {
        console.log(user);
        return this.http.post(environment.baseURL + '/user', user, { headers: new HttpHeaders().set('Content-Type', 'application/json') });
    }

    getUser():any {
        if (sessionStorage.getItem('user') !== null) {
            this.user = JSON.parse(sessionStorage.getItem('user'));
            this.loggedIn = true;
        } else if (localStorage.getItem('user') !== null) {
            this.user = JSON.parse(localStorage.getItem('user'));
            this.loggedIn = true;
        }
        if (!this.isAuthenticated()) {
            this.router.navigate(['/login']);
        } else {
            return this.user;
        }
    }

    hasRole(role: string): boolean {
        if (this.getUser().role == role) {
            return true;
        }
        return false;
    }
}