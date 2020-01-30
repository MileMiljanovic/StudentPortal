import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-departmani',
  templateUrl: './admin-departmani.component.html',
  styleUrls: ['./admin-departmani.component.css']
})
export class AdminDepartmaniComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/departmani').subscribe(
      (data) => {
        this.adminService.departmani = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
