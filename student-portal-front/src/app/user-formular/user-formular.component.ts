import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserManagerService } from '../user-manager.service';

@Component({
  selector: 'app-user-formular',
  templateUrl: './user-formular.component.html',
  styleUrls: ['./user-formular.component.css']
})
export class UserFormularComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private userService: UserManagerService
  ) {
    this.userService = JSON.parse(localStorage.getItem('userService'));
    this.token = localStorage.getItem('token');
   }

  ngOnInit() {
    const usr = this.userService.user;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/api/formulari', usr, {headers}).subscribe(
      (data) => {
        this.userService.formulari = data.formulari;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

  odgovor(f, odg) {

    const request = {
      formularId: f,
      odgovor: odg
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    if (this.userService.user.uloga === 'KOORDINATOR') {
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/koordinatorConfirm', request, {headers}).subscribe(
        (data) => {
          alert('Odgovor uspešno poslat!');
          const index = this.userService.formulari.indexOf(f, 0);
          if (index > -1) {
            this.userService.formulari.splice(index, 1);
          }
        },
        (err) => { alert('Došlo je do neočekivane greške!'); }
      );
    } else if (this.userService.user.uloga === 'SEF') {
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/sefConfirm', request, {headers}).subscribe(
        (data) => {
          alert('Odgovor uspešno poslat!');
          const index = this.userService.formulari.indexOf(f, 0);
          if (index > -1) {
            this.userService.formulari.splice(index, 1);
          }
        },
        (err) => { alert('Došlo je do neočekivane greške!'); }
      );
    } else {
      alert('Nepoznata uloga!');
    }

  }

}
