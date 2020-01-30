import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-programi-strani',
  templateUrl: './admin-programi-strani.component.html',
  styleUrls: ['./admin-programi-strani.component.css']
})
export class AdminProgramiStraniComponent implements OnInit {

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
    this.http.get<any>('http://localhost:8080/progStrani', {headers}).subscribe(
      (data) => {
        this.adminService.progStrani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteProgramStr(i) {
    const id = this.adminService.progStrani[i].naziv;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/progStrani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.progStrani.splice(i, 1);
        alert('Program ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
