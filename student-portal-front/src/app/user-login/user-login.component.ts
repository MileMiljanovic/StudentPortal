import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserManagerService } from '../user-manager.service';
import { AdminManagerService } from '../admin-manager.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserManagerService,
    private adminService: AdminManagerService
  ) {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
   }

  ngOnInit() {
  }

  onSubmit(login) {
    this.http.post<any>('http://localhost:8080/login', login).subscribe(
      (data) => {
        if (data.uloga === 'ADMIN') {
          this.adminService.user = data;
          localStorage.setItem('adminService', JSON.stringify(this.adminService));
          this.router.navigate(['/adminMain']);
        } else {
          this.userService.user = data;
          localStorage.setItem('userService', JSON.stringify(this.userService));
          this.router.navigate(['/userIndex']);
        }
      },
      (err) => { alert('Neuspešan login!'); }
    );
  }

}
