import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserManagerService } from '../user-manager.service';
import { StudentManagerService } from '../student-manager.service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  constructor(
    private router: Router,
    private userService: UserManagerService,
    private studentService: StudentManagerService,
  ) {
  }

  ngOnInit() {
    if (localStorage.getItem('userService') != null) {
      this.userService = JSON.parse(localStorage.getItem('userService'));
    }
    if (localStorage.getItem('studentService') != null) {
      this.studentService = JSON.parse(localStorage.getItem('studentService'));
    }
  }

  // tslint:disable-next-line: use-lifecycle-interface
  ngAfterContentChecked() {
    if (localStorage.getItem('userService') != null) {
      this.userService = JSON.parse(localStorage.getItem('userService'));
    }
    if (localStorage.getItem('studentService') != null) {
      this.studentService = JSON.parse(localStorage.getItem('studentService'));
    }
  }

  homePage() {
    if (this.userService.user) {
      this.router.navigate(['/userIndex']);
    } else if (this.studentService.student) {
      this.router.navigate(['/studentMainPage']);
    } else {
      this.router.navigate(['/']);
    }
  }

  logout() {
    if (this.userService.user) {
      this.userService.user = '';
      this.userService.formulari = [];
      localStorage.clear();
      this.router.navigate(['/']);
    } else if (this.studentService.student) {
      this.studentService.student = '';
      this.studentService.formular = '';
      this.studentService.formularId = '';
      this.studentService.izabraniProgram = '';
      this.studentService.predmetiDomaci = [];
      this.studentService.predmetiStrani = [];
      this.studentService.programiStrani = [];
      localStorage.clear();
      this.router.navigate(['/']);
    }
  }

}
