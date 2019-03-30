import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
@Injectable()
export class RoleGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) { }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> | boolean {
        var roles = route.data["roles"];
        if (roles && this.authService.isAuthenticated) {
            var hasValidRole: boolean = false;
            var currentUserRole = this.authService.getUser().role;
            for (let index = 0; index < roles.length; index++) {
                const role = roles[index];
                if (role == currentUserRole) {
                    hasValidRole = true;
                    break;
                }
            }
            if (!hasValidRole) {
                this.router.navigate(['/ticket']);
                return false;
            }
        }
        return true;
    }
}