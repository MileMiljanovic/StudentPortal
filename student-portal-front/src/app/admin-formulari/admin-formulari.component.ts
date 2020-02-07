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
  editZamenaForm;
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
      formular: '',
      predmetDomaci: {},
      predmetStrani: {}
    });
    this.editZamenaForm = this.formBuilder.group({
      idzamena: '',
      formular: '',
      predmetDomaci: {},
      predmetStrani: {}
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/formular', {headers}).subscribe(
      (data) => {
        this.adminService.formulari = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  openWithParamFormular(id: string, param) {
    this.modalService.openWithParamFormular(id, param);
    this.addZamenaForm.get('formular').setValue(param.idformular);
  }

  openWithParamZamena(id: string, param) {
    this.adminService.zamDomaci = this.adminService.predDomaci.filter(x => x.program.naziv === param.predmetDomaci.program.naziv);
    this.adminService.zamStrani = this.adminService.predStrani.filter(x => x.program.naziv === param.predmetStrani.program.naziv);
    this.modalService.openWithParamZamena(id, param);
    this.editZamenaForm.get('idzamena').setValue(param.idzamena);
    this.editZamenaForm.get('formular').setValue(param.formular);
    this.editZamenaForm.get('predmetDomaci').setValue(param.predmetDomaci);
    this.editZamenaForm.get('predmetStrani').setValue(param.predmetStrani);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.modalService.paramFormular = this.modalService.placeholderFormular;
      this.modalService.paramZamena = this.modalService.placeholderZamena;
      this.adminService.zamDomaci = this.adminService.predDomaci;
      this.adminService.zamStrani = this.adminService.predStrani;
      this.addFormularForm.reset();
      this.addZamenaForm.reset();
      this.editZamenaForm.reset();
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
    const tokenZam = uuid();
    const request = {
      idzamena: form.idzamena,
      formular: formular.idformular,
      predmetDomaci: form.predmetDomaci,
      predmetStrani: form.predmetStrani,
      odobreno: null,
      token: tokenZam
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

  editZamena(form, param) {
    if (form.predmetDomaci.espb > form.predmetStrani.espb) {
      alert('Strani predmet mora da ima više ili jednako ESPB bodova!');
      return;
    }
    const request = {
      idzamena: form.idzamena,
      formular: form.formular,
      predmetDomaci: form.predmetDomaci,
      predmetStrani: form.predmetStrani,
      odobreno: null,
      token: param.token
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.put<any>('http://localhost:8080/zamena/' + form.idzamena, request, {headers}).subscribe(
      (data) => {
        alert(form.idzamena + ' uspešno izmenjen!');
        this.editZamenaForm.reset();
        const indexF = this.adminService.formulari.findIndex(x => x.idformular === form.formular);
        const indexZ = this.adminService.formulari[indexF].zamene.findIndex(x => x.idzamena === form.idzamena);
        this.adminService.formulari[indexF].zamene[indexZ] = request;
        this.closeModal('editZamenaModal');
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

  confirmKoord(index, odg) {
    if (odg === this.adminService.formulari[index].odobrenjeKoord) {
      if (odg === 'Y') {
        alert('Već je odobreno!');
        return;
      } else {
        alert('Već je odbijeno!');
        return;
      }
    }
    if (this.adminService.formulari[index].odobrenjeSef === 'Y') {
      alert('Formular je zatvoren!');
      return;
    }
    const formular = this.adminService.formulari[index];
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    const request = {
      formularId: formular,
      odgovor: odg
    };
    this.http.put<any>('http://localhost:8080/api/formulari/' + formular.idformular + '/koordinatorConfirm', request, {headers}).subscribe(
      (data) => {
        alert('Odgovor uspešno poslat!');
        this.adminService.formulari[index].odobrenjeKoord = odg;
      },
      (err) => {
        alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  confirmSef(index, odg) {
    if (odg === this.adminService.formulari[index].odobrenjeSef) {
      if (odg === 'Y') {
        alert('Već je odobreno!');
        return;
      } else {
        alert('Već je odbijeno!');
        return;
      }
    }
    const checker = this.adminService.formulari[index].zamene.every(z => z.odobreno === 'Y');
    if (!checker) {
      alert('Morate prvo odobriti sve zamene!');
      return;
    }
    if (this.adminService.formulari[index].odobrenjeKoord !== 'Y') {
      alert('Koordinator nije odobrio!');
      return;
    }
    if (this.adminService.formulari[index].odobrenjeSef === 'Y') {
      alert('Formular je zatvoren!');
      return;
    }
    const formular = this.adminService.formulari[index];
    let total = 0;
    for (const zam of formular.zamene) {
      total += zam.predmetDomaci.espb;
    }
    if (total <= 10) {
      alert('Suma bodova domaćih predmeta je ' + total + '. Suma mora biti veća od 10!');
      return;
    }
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    const request = {
      formularId: formular,
      odgovor: odg
    };
    this.http.put<any>('http://localhost:8080/api/formulari/' + formular.idformular + '/sefConfirm', request, {headers}).subscribe(
      (data) => {
        alert('Odgovor uspešno poslat!');
        this.adminService.formulari[index].odobrenjeSef = odg;
      },
      (err) => {
        alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  confirmZamena(indexF, indexZ, odg) {
    const z = this.adminService.formulari[indexF].zamene[indexZ];
    if (odg === z.odobreno) {
      if (odg === 'Y') {
        alert('Već je odobreno!');
        return;
      } else {
        alert('Već je odbijeno!');
        return;
      }
    }
    if (this.adminService.formulari[indexF].odobrenjeKoord !== 'Y') {
      alert('Koordinator mora prvo da odobri formular!');
      return;
    }
    if (this.adminService.formulari[indexF].odobrenjeSef === 'Y') {
      alert('Formular je zatvoren!');
      return;
    }
    this.http.get<any>('http://localhost:8080/api/formulari/' + z.formular + '/zamene/' + z.idzamena + '/' + z.token + '/' + odg,
    {responseType: 'text' as 'json'}).subscribe(
      (data) => {
        alert('Odgovor uspešno poslat!');
        this.adminService.formulari[indexF].zamene[indexZ].odobreno = odg;
      },
      (err) => {
        alert(err.status + ' - ' + err.error.message);
      }
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
