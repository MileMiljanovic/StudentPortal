import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-predmeti-domaci',
  templateUrl: './admin-predmeti-domaci.component.html',
  styleUrls: ['./admin-predmeti-domaci.component.css']
})
export class AdminPredmetiDomaciComponent implements OnInit {

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
    this.http.get<any>('http://localhost:8080/predDomaci', {headers}).subscribe(
      (data) => {
        this.adminService.predDomaci = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deletePredmetDom(i) {
    const id = this.adminService.predDomaci[i].predmetId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/predDomaci/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.predDomaci.splice(i, 1);
        //BRISI I ZAMENE
        alert('Predmet ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
