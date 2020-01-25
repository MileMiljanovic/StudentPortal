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
  }

  initFormular() {
    this.http.post<any>('http://localhost:8080/api/podloga', this.studentService.student).subscribe(
      (data) => {
        console.log(data);
        this.studentService.programiStrani = data.programiStrani;
        this.router.navigate(['/chooseProgram']);
      },
      (err) => { alert('Neočekivana greška!'); }
    );
  }

}
