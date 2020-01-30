import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';

@Component({
  selector: 'app-admin-predmeti-strani',
  templateUrl: './admin-predmeti-strani.component.html',
  styleUrls: ['./admin-predmeti-strani.component.css']
})
export class AdminPredmetiStraniComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private adminService: AdminManagerService
  ) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/predStrani').subscribe(
      (data) => {
        this.adminService.predStrani = data;
      },
      (err) => { alert('Došlo je do neočekivane greške!'); }
    );
  }

}
