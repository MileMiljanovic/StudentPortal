import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-studenti',
  templateUrl: './admin-studenti.component.html',
  styleUrls: ['./admin-studenti.component.css']
})
export class AdminStudentiComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/student').subscribe(
      (data) => {
        this.adminService.studenti = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
