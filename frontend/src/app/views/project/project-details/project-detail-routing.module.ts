import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectDetailComponent } from './project-detail.component';
import { ProjectDetailDefaultComponent } from './default/project-detail-default.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectDetailComponent,
    children:[
      {
        path: ':categoryId',
        loadChildren: './category-details/category-detail.module#CategoryDetailModule',
      },
      {
        path: '',
        pathMatch: 'full',
        component: ProjectDetailDefaultComponent
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectDetailRoutingModule {}
