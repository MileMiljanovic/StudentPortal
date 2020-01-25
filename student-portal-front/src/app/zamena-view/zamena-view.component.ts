import { Component, OnInit } from '@angular/core';
import { StudentManagerService } from '../student-manager.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zamena-view',
  templateUrl: './zamena-view.component.html',
  styleUrls: ['./zamena-view.component.css']
})
export class ZamenaViewComponent implements OnInit {

  constructor(
    private studentService: StudentManagerService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
  }

  cancelFormular() {
    const toCancel = this.studentService.formular.idformular;
    this.http.delete<any>('http://localhost:8080/api/podloga/' + toCancel + '/cancelForm').subscribe(
      (data) => {
        alert(data.message);
        this.studentService.programiStrani = [];
        this.studentService.izabraniProgram = '';
        this.studentService.predmetiDomaci = [];
        this.studentService.predmetiStrani = [];
        this.studentService.formular = '';
        this.router.navigate(['/studentMainPage']);
      },
      (err) => { alert(err); }
    );
  }

}
