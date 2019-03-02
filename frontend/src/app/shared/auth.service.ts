import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment.prod';

@Injectable()
export class AuthService {
    user:any;
    loggedIn = false;
    constructor(private http: HttpClient) {
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
}