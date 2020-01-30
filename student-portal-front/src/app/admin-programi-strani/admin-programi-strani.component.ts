import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-programi-strani',
  templateUrl: './admin-programi-strani.component.html',
  styleUrls: ['./admin-programi-strani.component.css']
})
export class AdminProgramiStraniComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/progStrani').subscribe(
      (data) => {
        this.adminService.progStrani = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
