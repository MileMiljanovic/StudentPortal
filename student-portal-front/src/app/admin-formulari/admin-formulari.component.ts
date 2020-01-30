import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-formulari',
  templateUrl: './admin-formulari.component.html',
  styleUrls: ['./admin-formulari.component.css']
})
export class AdminFormulariComponent implements OnInit {

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
    this.http.get<any>('http://localhost:8080/formular', {headers}).subscribe(
      (data) => {
        this.adminService.formulari = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  deleteFormular(i) {
    const id = this.adminService.formulari[i].idformular;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/formular/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.formulari.splice(i, 1);
        alert('Formular ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }
    deleteZamena(i, j) {
      const id = this.adminService.formulari[i].zamene[j].idzamena;
      const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
      this.http.delete<any>('http://localhost:8080/zamena/' + id, {headers}).subscribe(
        (data) => {
          this.adminService.formulari[i].zamene.splice(j, 1);
          alert('Zamena ' + id + ' uspešno obrisana!');
        },
        (err) => { alert(err.status + ' - ' + err.error.message); }
      );
    }
  

}
