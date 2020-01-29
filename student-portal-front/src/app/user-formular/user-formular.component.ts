import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserManagerService } from '../user-manager.service';

@Component({
  selector: 'app-user-formular',
  templateUrl: './user-formular.component.html',
  styleUrls: ['./user-formular.component.css']
})
export class UserFormularComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private userService: UserManagerService
  ) {
    this.userService = JSON.parse(localStorage.getItem('userService'));
   }

  ngOnInit() {
    const usr = this.userService.user;
    this.http.post<any>('http://localhost:8080/api/formulari', usr).subscribe(
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
    if (this.userService.user.uloga === 'KOORDINATOR') {
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/koordinatorConfirm', request).subscribe(
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
      this.http.put<any>('http://localhost:8080/api/formulari/' + f.idformular + '/sefConfirm', request).subscribe(
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
