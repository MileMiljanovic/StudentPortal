import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-nastavnici',
  templateUrl: './admin-nastavnici.component.html',
  styleUrls: ['./admin-nastavnici.component.css']
})
export class AdminNastavniciComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) {
    this.token = localStorage.getItem('token');
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

  deleteNastavnik(i) {
    const id = this.adminService.nastavnici[i].nastavnikid;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/nastavnik/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.nastavnici.splice(i, 1);
        alert('Nastavnik ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
