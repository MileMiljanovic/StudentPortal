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
    if (localStorage.getItem('studentService') != null) {
      this.studentService = JSON.parse(localStorage.getItem('studentService'));
      if (!this.studentService.student) {
        this.router.navigate(['/']);
      }
    } else {
      this.router.navigate(['/']);
    }
    if (localStorage.getItem('zamene') != null) {
      this.zamene = JSON.parse(localStorage.getItem('zamene'));
    }
    if (localStorage.getItem('counter') != null) {
      // tslint:disable-next-line: radix
      this.counter = parseInt(localStorage.getItem('counter'));
    }
  }

  changeDomaci(p, i) {
    this.zamene[i].predmetDomaci = p;
    localStorage.setItem('zamene', JSON.stringify(this.zamene));
  }

  changeStrani(p, i) {
    this.zamene[i].predmetStrani = p;
    localStorage.setItem('zamene', JSON.stringify(this.zamene));
  }

  addZamena() {
    this.newZamena = { idzamena: this.studentService.formularId + '-' + this.counter,
      formular: this.studentService.formularId,
      predmetDomaci: {},
      predmetStrani: {},
      odobreno: null };
    this.zamene.push(this.newZamena);
    this.counter++;
    localStorage.setItem('zamene', JSON.stringify(this.zamene));
    localStorage.setItem('counter', this.counter.toString());
  }

  deleteZamena(index) {
    this.zamene.splice(index, 1);
    localStorage.setItem('zamene', JSON.stringify(this.zamene));
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
          alert('Postoji duplikat u domaćim predmetima: ' + x);
          return;
        }
        if (y === this.zamene[j].predmetStrani) {
          alert('Postoji duplikat u stranim predmetima: ' + y);
          return;
        }
      }
    }

    const reqZamene = [];
    this.zamene.forEach( (zam) => {
      const zamElement = JSON.parse(JSON.stringify(zam));
      const predmetDom = this.studentService.predmetiDomaci.find(x => x.naziv === zam.predmetDomaci);
      zamElement.predmetDomaci = predmetDom;
      const predmetStr = this.studentService.predmetiStrani.find(x => x.naziv === zam.predmetStrani);
      zamElement.predmetStrani = predmetStr;
      reqZamene.push(zamElement);
    });

    const request = {
      student: this.studentService.student,
      programStrani: this.studentService.izabraniProgram,
      formularId: this.studentService.formularId,
      zamene: reqZamene
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
        localStorage.setItem('studentService', JSON.stringify(this.studentService));
        localStorage.removeItem('zamene');
        localStorage.removeItem('counter');
        this.router.navigate(['/studentMainPage']);
      },
      (err) => {
        alert(err.message);
      }
    );
  }

}
