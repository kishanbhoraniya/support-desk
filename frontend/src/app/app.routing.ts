import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

/* Services
=========================================== */
import { LoginGuard } from './shared/login-guard.service';
import { AuthGuard } from './shared/auth-guard.service';

import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { RoleGuard } from './shared/role-guard.service';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    canActivate: [LoginGuard],
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    canActivate: [LoginGuard],
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    canActivate: [AuthGuard],
    data: {
      title: 'Home'
    },
    children: [
      {
        canActivate: [RoleGuard],
        data: {
          "roles": ["admin", "manager"]
        },
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'project',
        canActivate: [RoleGuard],
        data: {
          "roles": ["admin", "manager"]
        },
        loadChildren: './views/project/project.module#ProjectModule'
      },
      {
        path: 'user',
        canActivate: [RoleGuard],
        data: {
          "roles": ["admin"]
        },
        loadChildren: './views/users/user.module#UserModule'
      },
      {
        path: 'ticket',
        canActivate: [RoleGuard],
        loadChildren: './views/ticket/ticket.module#TicketModule'
      },
      {
        path: 'ticketmanage',
        canActivate: [RoleGuard],
        data: {
          "roles": ["admin", "manager", "agent"]
        },
        loadChildren: './views/ticketmanage/ticketmanage.module#TicketManageModule'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
