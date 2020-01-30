import { Component, OnInit } from '@angular/core';
import { StudentManagerService } from '../student-manager.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-main',
  templateUrl: './student-main.component.html',
  styleUrls: ['./student-main.component.css']
})
export class StudentMainComponent implements OnInit {

  constructor(
    private studentService: StudentManagerService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    if (localStorage.getItem('studentService') != null) {
      this.studentService = JSON.parse(localStorage.getItem('studentService'));
      if (!this.studentService.student) {
        this.router.navigate(['/']);
      }
    } else {
      this.router.navigate(['/']);
    }
  }

  initFormular() {
    this.http.post<any>('http://localhost:8080/api/podloga', this.studentService.student).subscribe(
      (data) => {
        if (data.f) {
          alert('Već imate aktivan formular!');
          this.studentService.formular = data.f;
          localStorage.setItem('studentService', JSON.stringify(this.studentService));
          this.router.navigate(['/zameneView']);
        } else {
          this.studentService.programiStrani = data.programiStrani;
          localStorage.setItem('studentService', JSON.stringify(this.studentService));
          this.router.navigate(['/chooseProgram']);
        }
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
