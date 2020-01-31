import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserManagerService } from '../user-manager.service';
import { AdminManagerService } from '../admin-manager.service';
import { StudentManagerService } from '../student-manager.service';

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
    private adminService: AdminManagerService,
    private studentService: StudentManagerService
  ) {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
   }

  ngOnInit() {
    if (localStorage.getItem('adminService') != null) {
      this.adminService = JSON.parse(localStorage.getItem('adminService'));
      if (this.adminService.user) {
        this.router.navigate(['/adminMain']);
      }
    }
    if (localStorage.getItem('userService') != null) {
      this.userService = JSON.parse(localStorage.getItem('userService'));
      if (this.userService.user) {
        this.router.navigate(['/userIndex']);
      }
    }
    if (localStorage.getItem('studentService') != null) {
      this.studentService = JSON.parse(localStorage.getItem('studentService'));
      if (this.studentService.student) {
        this.router.navigate(['/studentMainPage']);
      }
    }
  }

  onSubmit(login) {
    const token = btoa(login.username + ':' + login.password);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + token });
    this.http.post<any>('http://localhost:8080/login', login, {headers}).subscribe(
      (data) => {
        if (data.uloga === 'ADMIN') {
          this.adminService.user = data;
          localStorage.setItem('adminService', JSON.stringify(this.adminService));
          localStorage.setItem('token', token);
          this.router.navigate(['/adminMain']);
        } else {
          this.userService.user = data;
          localStorage.setItem('userService', JSON.stringify(this.userService));
          localStorage.setItem('token', token);
          this.router.navigate(['/userIndex']);
        }
      },
      (err) => { alert('Neuspešan login!'); }
    );
  }

}
