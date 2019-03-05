import { Injectable } from "@angular/core";
import { Router, ActivatedRoute, NavigationEnd } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs/index";
import { filter } from "rxjs/operators";

@Injectable()
export class AppBreadcrumbService {
  breadcrumbs: Observable<Array<Object>>;
  breadCrumbsSubject: BehaviorSubject<Array<Object>>;
  constructor(private router: Router, private route: ActivatedRoute) {
    this.breadCrumbsSubject = new BehaviorSubject<Object[]>(
      new Array<Object>()
    );
    this.breadcrumbs = this.breadCrumbsSubject.asObservable();
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(event => {
        const breadcrumbs = [];
        this.breadCrumbsSubject.next(Object.assign([], breadcrumbs));
        return breadcrumbs;
      });
  }
}
