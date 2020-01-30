import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-departmani',
  templateUrl: './admin-departmani.component.html',
  styleUrls: ['./admin-departmani.component.css']
})
export class AdminDepartmaniComponent implements OnInit {

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
    this.http.get<any>('http://localhost:8080/departmani', {headers}).subscribe(
      (data) => {
        this.adminService.departmani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteDepartman(i) {
    const id = this.adminService.departmani[i].departmanId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/departmani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.departmani.splice(i, 1);
        alert('Departman ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
