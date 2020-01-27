import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserManagerService } from '../user-manager.service';

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private router: Router,
    private userService: UserManagerService
  ) { }

  ngOnInit() {
  }

}
