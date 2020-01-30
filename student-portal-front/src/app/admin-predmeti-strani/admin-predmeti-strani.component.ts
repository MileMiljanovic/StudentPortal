import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-predmeti-strani',
  templateUrl: './admin-predmeti-strani.component.html',
  styleUrls: ['./admin-predmeti-strani.component.css']
})
export class AdminPredmetiStraniComponent implements OnInit {

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
    this.http.get<any>('http://localhost:8080/predStrani', {headers}).subscribe(
      (data) => {
        this.adminService.predStrani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deletePredmetStr(i) {
    const id = this.adminService.predStrani[i].predmetId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/predStrani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.predStrani.splice(i, 1);
        //BRISI I ZAMENE
        alert('Predmet ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
