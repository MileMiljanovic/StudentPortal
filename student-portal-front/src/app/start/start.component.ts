import { Component, OnInit } from '@angular/core';
import { StudentManagerService } from '../student-manager.service';
import { UserManagerService } from '../user-manager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  constructor(
    private userService: UserManagerService,
    private router: Router,
    private studentService: StudentManagerService
  ) { }

  ngOnInit() {
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

}
