import { Component, OnInit } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { MessageService } from "primeng/api";

@Component({
  selector: "body",
  templateUrl: "app.component.html",
  providers: [MessageService]
})
export class AppComponent implements OnInit {
  constructor(private router: Router, private messageService: MessageService) {}

  ngOnInit() {
    this.router.events.subscribe(evt => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0);
    });
  }
}
