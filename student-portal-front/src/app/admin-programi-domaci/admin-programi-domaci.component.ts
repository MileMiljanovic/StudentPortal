import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-programi-domaci',
  templateUrl: './admin-programi-domaci.component.html',
  styleUrls: ['./admin-programi-domaci.component.css']
})
export class AdminProgramiDomaciComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/progDomaci').subscribe(
      (data) => {
        this.adminService.progDomaci = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
