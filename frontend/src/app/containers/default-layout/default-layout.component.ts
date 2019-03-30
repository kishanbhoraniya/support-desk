import { Component, OnDestroy, Inject, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { NavData } from '../../_nav';
import { AuthService } from "../../shared/auth.service";
import { Router } from "@angular/router";


@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit, OnDestroy {
  public navItems;
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement;
  constructor(private authService: AuthService, private router: Router, @Inject(DOCUMENT) _document?: any) {
    this.changes = new MutationObserver((mutations) => {
      this.sidebarMinimized = _document.body.classList.contains('sidebar-minimized');
    });
    this.element = _document.body;
    this.changes.observe(<Element>this.element, {
      attributes: true,
      attributeFilter: ['class']
    });
    this.navItems = this.getNavItems();
  }
  logout() {
    sessionStorage.removeItem('user');
    localStorage.removeItem('user');
    this.authService.loggedIn = false;
    this.router.navigate(['/login']);
  }
  ngOnInit(): void {
    this.navItems = this.getNavItems();
  }
  ngOnDestroy(): void {
    this.changes.disconnect();
  }
  getNavItems() {
    var navItems: NavData[] = [
      {
        name: 'Dashboard',
        url: '/dashboard',
        icon: 'icon-speedometer',
        attributes: {
          role: ['admin', 'manager']
        }
      },
      {
        name: 'Projects',
        url: '/project',
        icon: 'icon-puzzle',
        attributes: {
          role: ['admin', 'manager']
        }
      },
      {
        name: 'Users',
        url: '/user',
        icon: 'icon-people',
        attributes: {
          role: ['admin']
        }
      },
      {
        name: 'Manage Ticket',
        url: '/ticketmanage',
        icon: 'icon-envelope',
        attributes: {
          role: ['admin', 'manager', 'agent']
        }
      },
      {
        name: 'Ticket',
        url: '/ticket',
        icon: 'icon-envelope',
        attributes: {
          role: ['admin', 'manager', 'agent', 'user']
        }
      }
    ]
    var navItemsFiltered: NavData[] = [];
    var currentUserRole = this.authService.getUser().role;
    navItems.forEach(navItem => {
      if (navItem.attributes && navItem.attributes.role && navItem.attributes.role.length > 0) {
        var hasValidRole: boolean = false;
        for (let index = 0; index < navItem.attributes.role.length; index++) {
          const role = navItem.attributes.role[index];
          if (role == currentUserRole) {
            hasValidRole = true;
            break;
          }
        }
        if (hasValidRole) {
          navItemsFiltered.push(navItem);
        }
      } else {
        navItemsFiltered.push(navItem);
      }
    });
    return navItemsFiltered;
  }
}
