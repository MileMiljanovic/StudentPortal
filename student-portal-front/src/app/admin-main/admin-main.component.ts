import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit {

  constructor(
    private router: Router,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    if (localStorage.getItem('adminService') != null) {
      this.adminService = JSON.parse(localStorage.getItem('adminService'));
      if (!this.adminService.user) {
        this.router.navigate(['/']);
      }
    } else {
      this.router.navigate(['/']);
    }
  }

}
