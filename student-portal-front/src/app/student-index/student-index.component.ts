import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { StudentManagerService } from '../student-manager.service';
import { UserManagerService } from '../user-manager.service';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-student-index',
  templateUrl: './student-index.component.html',
  styleUrls: ['./student-index.component.css']
})
export class StudentIndexComponent implements OnInit {

  indexForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private studentService: StudentManagerService,
    private userService: UserManagerService,
    private adminService: AdminManagerService
  ) {
    this.indexForm = this.formBuilder.group({
      brIndeksa: ''
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

  onSubmit(index) {
    this.http.post<any>('http://localhost:8080/indexValidation', index).subscribe(
      (data) => {
        this.studentService.student = data;
        localStorage.setItem('studentService', JSON.stringify(this.studentService));
        this.router.navigate(['/studentMainPage']);
      },
      (err) => { alert('Neuspešan login!'); }
    );
  }

}
