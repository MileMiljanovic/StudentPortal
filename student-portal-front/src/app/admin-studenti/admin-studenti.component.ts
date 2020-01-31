import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-studenti',
  templateUrl: './admin-studenti.component.html',
  styleUrls: ['./admin-studenti.component.css']
})
export class AdminStudentiComponent implements OnInit {

  token;
  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) {
    this.token = localStorage.getItem('token');
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/student', {headers}).subscribe(
      (data) => {
        this.adminService.studenti = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteStudent(i) {
    const id = this.adminService.studenti[i].brindeksa;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/student/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.studenti.splice(i, 1);
        //BRISI I FORMULAR
        alert('Student ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
