import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from './project.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { from } from 'rxjs';

const routes: Routes = [
  {
    path: '',
    component: ProjectComponent,
    data: {
      title: 'Project'
    },
    children:[
      {
        path: ':projectId',
        loadChildren: './project-details/project-detail.module#ProjectDetailModule',
      },
      {
        path: '',
        pathMatch: 'full',
        component: ProjectListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectRoutingModule {}
