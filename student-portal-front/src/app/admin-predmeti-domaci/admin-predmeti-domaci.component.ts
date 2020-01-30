import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-predmeti-domaci',
  templateUrl: './admin-predmeti-domaci.component.html',
  styleUrls: ['./admin-predmeti-domaci.component.css']
})
export class AdminPredmetiDomaciComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/predDomaci').subscribe(
      (data) => {
        this.adminService.predDomaci = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
