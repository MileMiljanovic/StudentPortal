import { Component, OnInit } from '@angular/core';
import { StudentManagerService } from '../student-manager.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Zamena } from '../models/zamena';

@Component({
  selector: 'app-zamena',
  templateUrl: './zamena.component.html',
  styleUrls: ['./zamena.component.css']
})
export class ZamenaComponent implements OnInit {

  counter: number;
  zamene: Array<Zamena> = [];
  newZamena: Zamena;
  constructor(
    private studentService: StudentManagerService,
    private http: HttpClient,
    private router: Router
  ) {
    this.counter = 1;
  }

  ngOnInit() {
  }

  addZamena() {
    this.newZamena = { idzamena: this.studentService.formularId + '-' + this.counter,
      formular: this.studentService.formularId,
      predmetDomaci: {},
      predmetStrani: {},
      odobreno: null };
    this.zamene.push(this.newZamena);
    this.counter++;
  }

  /*confirmZamena(index, domaci, strani) {
    if (Object.keys(domaci).length === 0) {
      alert('Odaberite domaći predmet!');
      return;
    }
    if (Object.keys(strani).length === 0) {
      alert('Odaberite strani predmet!');
      return;
    }
    this.zamene[index].predmetDomaci = domaci;
    this.zamene[index].predmetStrani = strani;
    console.log(this.zamene);
  }*/

  deleteZamena(index) {
    this.zamene.splice(index, 1);
  }

  confirmFormular() {

    console.log(this.zamene);
    if (this.zamene.length === 0) {
      alert('Mora postojati bar jedna zamena!');
      return;
    }
    for (let i = 0; i < this.zamene.length; i++) {
      const x = this.zamene[i].predmetDomaci;
      const y = this.zamene[i].predmetStrani;
      if (Object.keys(x).length === 0) {
        alert('Odaberite sve domaće predmete!');
        return;
      }
      if (Object.keys(y).length === 0) {
        alert('Odaberite sve strane predmete!');
        return;
      }
      for (let j = i + 1; j < this.zamene.length; j++) {
        if (x === this.zamene[j].predmetDomaci) {
          alert('Postoji duplikat u domaćim predmetima: ' + x.naziv);
          return;
        }
        if (y === this.zamene[j].predmetStrani) {
          alert('Postoji duplikat u stranim predmetima: ' + y.naziv);
          return;
        }
      }
    }

    const request = {
      student: this.studentService.student,
      programStrani: this.studentService.izabraniProgram,
      formularId: this.studentService.formularId,
      zamene: this.zamene
    };
    console.log(request);
    this.http.post<any>('http://localhost:8080/api/podloga/' + this.studentService.formularId
    + '/zamene', request).subscribe(
      (data) => {
        alert(data.message);
        this.studentService.programiStrani = [];
        this.studentService.izabraniProgram = '';
        this.studentService.predmetiDomaci = [];
        this.studentService.predmetiStrani = [];
        this.studentService.formular = '';
        this.studentService.formularId = '';
        this.router.navigate(['/studentMainPage']);
      },
      (err) => { alert(err.message); }
    );
  }

}
