import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';
import {MatDatepickerModule, MatDateFormats} from '@angular/material';

@Component({
  selector: 'app-admin-korisnici',
  templateUrl: './admin-korisnici.component.html',
  styleUrls: ['./admin-korisnici.component.css']
})
export class AdminKorisniciComponent implements OnInit {

  token;
  korisnikForm;
  editKorisnikForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.korisnikForm = this.formBuilder.group({
      username: '',
      password: '',
      passwordConf: '',
      ime: '',
      prezime: '',
      jmbg: '',
      datumRodjenja: '',
      uloga: ''
    });
    this.editKorisnikForm = this.formBuilder.group({
      username: '',
      password: '',
      passwordConf: '',
      ime: '',
      prezime: '',
      jmbg: '',
      datumRodjenja: '',
      uloga: ''
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/korisnik', {headers}).subscribe(
      (data) => {
        this.adminService.korisnici = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.modalService.paramKorisnik = this.modalService.placeholderKorisnik;
      this.korisnikForm.reset();
      this.editKorisnikForm.reset();
  }

  openWithParamKorisnik(id: string, param) {
    this.modalService.openWithParamKorisnik(id, param);
    this.editKorisnikForm.get('username').setValue(param.username);
    this.editKorisnikForm.get('ime').setValue(param.ime);
    this.editKorisnikForm.get('prezime').setValue(param.prezime);
    this.editKorisnikForm.get('jmbg').setValue(param.jmbg);
    this.editKorisnikForm.get('uloga').setValue(param.uloga);
  }

  addKorisnik(form) {
    if (!form.username) {
      alert('Unesite korisničko ime!');
      return;
    }
    if (!form.password) {
      alert('Unesite lozinku!');
      return;
    }
    if (form.password !== form.passwordConf) {
      alert('Lozinke se ne podudaraju!');
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
    if (!form.datumRodjenja) {
      alert('Unesite datum!');
      return;
    }
    if (!form.uloga) {
      alert('Izaberite ulogu!');
      return;
    }
    const dateFormatted = this.dateAsYYYYMMDD(form.datumRodjenja);
    const request = {
      username: form.username,
      password: form.password,
      ime: form.ime,
      prezime: form.prezime,
      jmbg: form.jmbg,
      datumrodjenja: dateFormatted,
      uloga: form.uloga
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/korisnik', request, {headers}).subscribe(
      (data) => {
        this.adminService.korisnici.push(request);
        alert(form.username + ' uspešno dodat!');
        this.korisnikForm.reset();
        this.adminService.korisnici.sort((a, b) => (a.username > b.username) ? 1 : -1);
        this.closeModal('addKorisnikModal');
        this.http.get<any>('http://localhost:8080/korisnik', {headers}).subscribe(
          (data1) => {
            this.adminService.korisnici = data1;
          },
          (err) => { alert(err.status + ' - ' + err.error.message); }
        );
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );

  }

  editKorisnik(form, kor) {
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
    if (form.password === form.passwordConf) {
      const datumRodj = (!form.datumRodjenja) ? kor.datumrodjenja : this.dateAsYYYYMMDD(form.datumRodjenja);
      const newPass = (!form.password) ? kor.password : form.password;
      const request = {
        username: form.username,
        password: newPass,
        ime: form.ime,
        prezime: form.prezime,
        jmbg: form.jmbg,
        datumrodjenja: datumRodj,
        uloga: form.uloga
      };
      const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
      this.http.put<any>('http://localhost:8080/korisnik/' + kor.username, request, {headers}).subscribe(
        (data) => {
          alert(kor.username + ' uspešno izmenjen!');
          this.editKorisnikForm.reset();
          const index = this.adminService.korisnici.findIndex(x => x.username === form.username);
          this.adminService.korisnici[index] = data;
          this.closeModal('editKorisnikModal');
          console.log(data);
        },
        (err) => {
            alert(err.status + ' - ' + err.error.message);
        }
      );
    } else {
      alert('Lozinke se ne podudaraju!');
    }
  }

  deleteKorisnik(i) {
    const id = this.adminService.korisnici[i].username;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/korisnik/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.korisnici.splice(i, 1);
        alert('Korisnik ' + id + ' uspešno obrisan!');
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
