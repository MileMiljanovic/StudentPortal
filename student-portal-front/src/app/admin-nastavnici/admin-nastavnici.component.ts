import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-nastavnici',
  templateUrl: './admin-nastavnici.component.html',
  styleUrls: ['./admin-nastavnici.component.css']
})
export class AdminNastavniciComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/nastavnik').subscribe(
      (data) => {
        this.adminService.nastavnici = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
