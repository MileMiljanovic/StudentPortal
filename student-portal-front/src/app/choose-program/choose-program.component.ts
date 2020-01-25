import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { StudentManagerService } from '../student-manager.service';

@Component({
  selector: 'app-choose-program',
  templateUrl: './choose-program.component.html',
  styleUrls: ['./choose-program.component.css']
})
export class ChooseProgramComponent implements OnInit {

  selectedValue;
  constructor(
    private http: HttpClient,
    private router: Router,
    private studentService: StudentManagerService
  ) { }

  ngOnInit() {
  }

  confirmProgram() {

    if (!this.selectedValue) {
      alert('Odaberite strani studentski program!');
      return;
    }
    this.studentService.izabraniProgram = this.selectedValue;

    const request = {
      student: this.studentService.student,
      programStrani: this.studentService.izabraniProgram
    };

    this.http.post<any>('http://localhost:8080/api/podloga/straniProgram', request).subscribe(
      (data) => {
        this.studentService.predmetiDomaci = data.predmetiDomaci;
        this.studentService.predmetiStrani = data.predmetiStrani;
        this.studentService.formularId = data.formularId;
        this.router.navigate(['/zamene']);
      },
      (err) => { alert('Neočekivana greška!'); }
    );
  }

}