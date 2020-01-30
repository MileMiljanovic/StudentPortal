import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-formulari',
  templateUrl: './admin-formulari.component.html',
  styleUrls: ['./admin-formulari.component.css']
})
export class AdminFormulariComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/formular').subscribe(
      (data) => {
        this.adminService.formulari = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
