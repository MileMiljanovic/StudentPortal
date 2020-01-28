import { Component, OnInit } from '@angular/core';
import { UserManagerService } from '../user-manager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent implements OnInit {

  constructor(
    private userService: UserManagerService,
    private router: Router,
  ) {}

  ngOnInit() {
    if (localStorage.getItem('userService') != null) {
      this.userService = JSON.parse(localStorage.getItem('userService'));
      if (!this.userService.user) {
        this.router.navigate(['/']);
      }
    } else {
      this.router.navigate(['/']);
    }
  }

}
