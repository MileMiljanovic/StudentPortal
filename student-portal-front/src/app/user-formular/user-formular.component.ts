import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserManagerService } from '../user-manager.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-user-formular',
  templateUrl: './user-formular.component.html',
  styleUrls: ['./user-formular.component.css']
})
export class UserFormularComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private userService: UserManagerService,
    private spinnerService: Ng4LoadingSpinnerService
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
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  odgovor(f, odg) {

    this.spinnerService.show();
    const request = {
      formularId: f,
      odgovor: odg
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    if (this.userService.user.uloga === 'KOORDINATOR') {
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/koordinatorConfirm', request, {headers}).subscribe(
        (data) => {
          this.spinnerService.hide();
          const index = this.userService.formulari.indexOf(f, 0);
          if (index > -1) {
            this.userService.formulari.splice(index, 1);
          }
          alert('Odgovor uspešno poslat!');
        },
        (err) => {
          this.spinnerService.hide();
          alert(err.status + ' - ' + err.error.message);
        }
      );
    } else if (this.userService.user.uloga === 'SEF') {
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/sefConfirm', request, {headers}).subscribe(
        (data) => {
          this.spinnerService.hide();
          const index = this.userService.formulari.indexOf(f, 0);
          if (index > -1) {
            this.userService.formulari.splice(index, 1);
          }
          alert('Odgovor uspešno poslat!');
        },
        (err) => {
          this.spinnerService.hide();
          alert(err.status + ' - ' + err.error.message);
        }
      );
    } else {
      alert('Nepoznata uloga!');
    }

  }

}
