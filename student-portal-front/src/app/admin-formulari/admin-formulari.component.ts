import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { FormBuilder } from '@angular/forms';
import { ModalService } from '../_modal';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'app-admin-formulari',
  templateUrl: './admin-formulari.component.html',
  styleUrls: ['./admin-formulari.component.css']
})
export class AdminFormulariComponent implements OnInit {

  token;
  addFormularForm;
  addZamenaForm;
  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService,
    private formBuilder: FormBuilder,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.addFormularForm = this.formBuilder.group({
      formularId: '',
      selectedStudent: {},
      selectedProgram: {}
    });
    this.addZamenaForm = this.formBuilder.group({
      idzamena: '',
      predmetDomaci: {},
      predmetStrani: {}
    });
   }

  ngOnInit() {
   
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/formular', {headers}).subscribe(
      (data) => {
        this.adminService.formulari = data;
        this.modalService.param = this.adminService.formulari[0];
        console.log(this.modalService.param);
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  openModalWithParam(id: string, param) {
    this.modalService.openWithParam(id, param);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.addFormularForm.reset();
      this.addZamenaForm.reset();
  }

  addFormular(addForm) {
    if (!addForm.selectedStudent) {
      alert('Izaberite studenta!');
      return;
    }
    if (Object.keys(addForm.selectedStudent).length === 0) {
      alert('Izaberite studenta!');
      return;
    }
    if (!addForm.selectedProgram) {
      alert('Izaberite program!');
      return;
    }
    if (Object.keys(addForm.selectedProgram).length === 0) {
      alert('Izaberite program!');
      return;
    }
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/formular/getId', {headers}).subscribe(
      (data) => {
        const curTime = this.dateAsYYYYMMDDHHNNSS(new Date());
        const id = data.message;
        const request = {
          idformular: id,
          student: addForm.selectedStudent,
          programStrani: addForm.selectedProgram,
          odobrenjeSef: null,
          odobrenjeKoord: null,
          datum: curTime,
          zamene: []
        };
        this.http.post<any>('http://localhost:8080/formular', request, {headers}).subscribe(
          (data1) => {
            this.adminService.formulari.push(request);
            alert(id + ' uspešno dodat!');
            this.addFormularForm.reset();
            this.adminService.formulari.sort((a, b) => (a.idformular > b.idformular) ? 1 : -1);
            this.closeModal('addFormularModal');
          },
          (err1) => { alert(err1.status + ' - ' + err1.error.message); }
        );
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  addZamena(form, formular) {
    console.log(formular);
    if (!form.idzamena) {
      alert('Unesite ID zamene!');
      return;
    }
    if (!form.predmetDomaci) {
      alert('Izaberite domaći predmet!');
      return;
    }
    if (Object.keys(form.predmetDomaci).length === 0) {
      alert('Izaberite domaći predmet!');
      return;
    }
    if (!form.predmetStrani) {
      alert('Izaberite strani predmet!');
      return;
    }
    if (Object.keys(form.predmetStrani).length === 0) {
      alert('Izaberite strani predmet!');
      return;
    }
    if (form.predmetDomaci.espb < form.predmetStrani.espb) {
      alert('Domaći predmet mora da nosi isto ili više bodova od stranog!');
      return;
    }
    for (let j = 0; j < formular.zamene.length; j++) {
      if (form.predmetDomaci.predmetId === formular.zamene[j].predmetDomaci.predmetId) {
        alert('Postoji duplikat u domaćim predmetima: ' + form.predmetDomaci.naziv);
        return;
      }
      if (form.predmetStrani.predmetId === formular.zamene[j].predmetStrani.predmetId) {
        alert('Postoji duplikat u stranim predmetima: ' + form.predmetStrani.naziv);
        return;
      }
    }
    const token = uuid();
    const request = {
      idzamena: form.idzamena,
      formular: formular.idformular,
      predmetDomaci: form.predmetDomaci,
      predmetStrani: form.predmetStrani,
      odobreno: null,
      token: token
    };
    const index = this.adminService.formulari.findIndex(x => x.idformular === formular.idformular);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/zamena', request, {headers}).subscribe(
      (data) => {
        this.adminService.formulari[index].zamene.push(request);
        alert(form.idzamena + ' uspešno dodata!');
        this.addZamenaForm.reset();
        this.closeModal('addZamenaModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deleteFormular(i) {
    const id = this.adminService.formulari[i].idformular;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/formular/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.formulari.splice(i, 1);
        alert('Formular ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteZamena(i, j) {
    const id = this.adminService.formulari[i].zamene[j].idzamena;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/zamena/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.formulari[i].zamene.splice(j, 1);
        alert('Zamena ' + id + ' uspešno obrisana!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  dateAsYYYYMMDDHHNNSS(date): string {
    return date.getFullYear()
              + '-' + this.leftpad(date.getMonth() + 1, 2)
              + '-' + this.leftpad(date.getDate(), 2)
              + ' ' + this.leftpad(date.getHours(), 2)
              + ':' + this.leftpad(date.getMinutes(), 2)
              + ':' + this.leftpad(date.getSeconds(), 2);
  }
  leftpad(val, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
          + String(val)).slice(String(val).length);
  }

}
