import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-programi-domaci',
  templateUrl: './admin-programi-domaci.component.html',
  styleUrls: ['./admin-programi-domaci.component.css']
})
export class AdminProgramiDomaciComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) {
    this.token = localStorage.getItem('token');
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/progDomaci', {headers}).subscribe(
      (data) => {
        this.adminService.progDomaci = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteProgramDom(i) {
    const id = this.adminService.progDomaci[i].naziv;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/progDomaci/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.progDomaci.splice(i, 1);
        alert('Program ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
