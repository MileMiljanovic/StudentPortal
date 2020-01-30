import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-zamene',
  templateUrl: './admin-zamene.component.html',
  styleUrls: ['./admin-zamene.component.css']
})
export class AdminZameneComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/zamena').subscribe(
      (data) => {
        this.adminService.zamene = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
