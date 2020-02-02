import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material';

@Component({
  selector: 'app-admin-nastavnici',
  templateUrl: './admin-nastavnici.component.html',
  styleUrls: ['./admin-nastavnici.component.css']
})
export class AdminNastavniciComponent implements OnInit {

  token;
  nastavnikForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.nastavnikForm = this.formBuilder.group({
      nastavnikId: '',
      ime: '',
      prezime: '',
      jmbg: '',
      datumRodjenja: '',
      email: ''
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/nastavnik', {headers}).subscribe(
      (data) => {
        this.adminService.nastavnici = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.nastavnikForm.reset();
  }

  addNastavnik(form) {
    if (!form.nastavnikId) {
      alert('Unesite broj indeksa!');
      return;
    }
    if (!form.ime) {
      alert('Unesite ime!');
      return;
    }
    if (!form.prezime) {
      alert('Unesite prezime!');
      return;
    }
    if (!form.jmbg) {
      alert('Unesite jmbg!');
      return;
    }
    if (!form.email) {
      alert('Unesite email!');
      return;
    }
    if (!form.datumRodjenja) {
      alert('Unesite datum!');
      return;
    }
    const dateFormatted = this.dateAsYYYYMMDD(form.datumRodjenja);
    const request = {
      nastavnikid: form.nastavnikId,
      ime: form.ime,
      prezime: form.prezime,
      jmbg: form.jmbg,
      datumrodjenja: dateFormatted,
      email: form.email
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/nastavnik', request, {headers}).subscribe(
      (data) => {
        this.adminService.nastavnici.push(request);
        alert(form.nastavnikId + ' uspešno dodat!');
        this.nastavnikForm.reset();
        this.adminService.nastavnici.sort((a, b) => (a.nastavnikid > b.nastavnikid) ? 1 : -1);
        this.closeModal('addNastavnikModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deleteNastavnik(i) {
    const id = this.adminService.nastavnici[i].nastavnikid;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/nastavnik/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.nastavnici.splice(i, 1);
        alert('Nastavnik ' + id + ' uspešno obrisan!');
      },
      (err) => {
        if (err.error.message.includes('ConstraintViolationException')) {
          alert('Brisanje onemogućeno zbog stranog ključa! Prvo obrišite sve podređene elemente.');
        } else {
          alert(err.status + ' - ' + err.error.message);
        }
      }
    );
  }

  dateAsYYYYMMDD(date): string {
    return date.getFullYear()
              + '-' + this.leftpad(date.getMonth() + 1, 2)
              + '-' + this.leftpad(date.getDate(), 2);
  }
  leftpad(val, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
          + String(val)).slice(String(val).length);
  }

}
