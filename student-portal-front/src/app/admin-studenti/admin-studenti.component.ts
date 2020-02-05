import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';
import {MatDatepickerModule, MatDateFormats} from '@angular/material';

@Component({
  selector: 'app-admin-studenti',
  templateUrl: './admin-studenti.component.html',
  styleUrls: ['./admin-studenti.component.css']
})
export class AdminStudentiComponent implements OnInit {

  token;
  studentForm;
  editStudentForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.studentForm = this.formBuilder.group({
      brIndeksa: '',
      ime: '',
      prezime: '',
      jmbg: '',
      datumRodjenja: '',
      email: '',
      studije: {}
    });
    this.editStudentForm = this.formBuilder.group({
      brIndeksa: '',
      ime: '',
      prezime: '',
      jmbg: '',
      datumRodjenja: '',
      email: '',
      studije: {}
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/student', {headers}).subscribe(
      (data) => {
        this.adminService.studenti = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  openWithParamStudent(id: string, param) {
    this.modalService.openWithParamStudent(id, param);
    this.editStudentForm.get('brIndeksa').setValue(param.brindeksa);
    this.editStudentForm.get('ime').setValue(param.ime);
    this.editStudentForm.get('prezime').setValue(param.prezime);
    this.editStudentForm.get('jmbg').setValue(param.jmbg);
    this.editStudentForm.get('email').setValue(param.email);
    this.editStudentForm.get('studije').setValue(param.studije);
  }


  closeModal(id: string) {
      this.modalService.close(id);
      this.modalService.paramStudent = this.modalService.placeholderStudent;
      this.studentForm.reset();
      this.editStudentForm.reset();
  }

  addStudent(form) {
    if (!form.brIndeksa) {
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
    if (!form.studije) {
      alert('Izaberite studije!');
      return;
    }
    if (Object.keys(form.studije).length === 0) {
      alert('Izaberite studije!');
      return;
    }
    const dateFormatted = this.dateAsYYYYMMDD(form.datumRodjenja);
    const request = {
      brindeksa: form.brIndeksa,
      ime: form.ime,
      prezime: form.prezime,
      jmbg: form.jmbg,
      datumrodjenja: dateFormatted,
      email: form.email,
      studije: form.studije
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/student', request, {headers}).subscribe(
      (data) => {
        this.adminService.studenti.push(request);
        alert(form.brIndeksa + ' uspešno dodat!');
        this.studentForm.reset();
        this.adminService.studenti.sort((a, b) => (a.brindeksa > b.brindeksa) ? 1 : -1);
        this.closeModal('addStudentModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  editStudent(form, stud) {
    if (!form.ime) {
      alert('Ime ne sme biti prazno!');
      return;
    }
    if (!form.prezime) {
      alert('Prezime ne sme biti prazno!');
      return;
    }
    if (!form.jmbg) {
      alert('JMBG ne sme biti prazan!');
      return;
    }
    if (!form.email) {
      alert('Email ne sme biti prazan!');
      return;
    }
    const datumRodj = (!form.datumRodjenja) ? stud.datumrodjenja : this.dateAsYYYYMMDD(form.datumRodjenja);
    const request = {
      brindeksa: form.brIndeksa,
      ime: form.ime,
      prezime: form.prezime,
      jmbg: form.jmbg,
      datumrodjenja: datumRodj,
      email: form.email,
      studije: form.studije
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.put<any>('http://localhost:8080/student/' + form.brIndeksa, request, {headers}).subscribe(
        (data) => {
          alert(form.brIndeksa + ' uspešno izmenjen!');
          this.editStudentForm.reset();
          const index = this.adminService.studenti.findIndex(x => x.brindeksa === form.brIndeksa);
          this.adminService.studenti[index] = request;
          this.closeModal('editStudentModal');
        },
        (err) => {
            alert(err.status + ' - ' + err.error.message);
        }
      );
  }

  deleteStudent(i) {
    const id = this.adminService.studenti[i].brindeksa;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    console.log(id);
    this.http.delete<any>('http://localhost:8080/student/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.studenti.splice(i, 1);
        this.adminService.formulari = this.adminService.formulari.filter(x => x.student.brindeksa !== id);
        alert('Student ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  dateAsYYYYMMDD(date): string {
    return date.getFullYear()
              + '-' + this.leftpad(date.getMonth() + 1, 2)
              + '-' + this.leftpad(date.getDate(), 2)
  }
  leftpad(val, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
          + String(val)).slice(String(val).length);
  }
}

