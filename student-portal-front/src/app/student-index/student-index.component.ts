import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { StudentManagerService } from '../student-manager.service';

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
    private studentService: StudentManagerService
  ) {
    this.indexForm = this.formBuilder.group({
      brIndeksa: ''
    });
  }

  ngOnInit() {
  }

  onSubmit(index) {
    this.http.post<any>('http://localhost:8080/indexValidation', index).subscribe(
      (data) => {
        this.studentService.student = data;
        this.router.navigate(['/studentMainPage']);
      },
      (err) => { alert('Neuspesan login!'); }
    );
  }

}
