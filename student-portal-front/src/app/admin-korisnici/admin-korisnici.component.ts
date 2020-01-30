import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-korisnici',
  templateUrl: './admin-korisnici.component.html',
  styleUrls: ['./admin-korisnici.component.css']
})
export class AdminKorisniciComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) {
    this.adminService = JSON.parse(localStorage.getItem('adminService'));
    this.token = localStorage.getItem('token');
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

  deleteKorisnik(i) {
    const id = this.adminService.korisnici[i].username;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/korisnik/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.korisnici.splice(i, 1);
        alert('Korisnik ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
