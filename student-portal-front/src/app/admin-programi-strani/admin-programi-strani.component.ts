import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-programi-strani',
  templateUrl: './admin-programi-strani.component.html',
  styleUrls: ['./admin-programi-strani.component.css']
})
export class AdminProgramiStraniComponent implements OnInit {

  token;
  programStraniForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.programStraniForm = this.formBuilder.group({
      naziv: ''
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/progStrani', {headers}).subscribe(
      (data) => {
        this.adminService.progStrani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.programStraniForm.reset();
  }

  addProgramStr(form) {
    if (!form.naziv) {
      alert('Unesite naziv!');
      return;
    }
    const request = {
      naziv: form.naziv
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/progStrani', request, {headers}).subscribe(
      (data) => {
        this.adminService.progStrani.push(request);
        alert(form.naziv + ' uspešno dodat!');
        this.programStraniForm.reset();
        this.adminService.progStrani.sort((a, b) => (a.naziv > b.naziv) ? 1 : -1);
        this.closeModal('addProgramStraniModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deleteProgramStr(i) {
    const id = this.adminService.progStrani[i].naziv;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/progStrani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.progStrani.splice(i, 1);
        alert('Program ' + id + ' uspešno obrisan!');
      },
      (err) => {
        if (err.error.message.includes('ConstraintViolationException')) {
          alert('Brisanje onemogućeno zbog stranog ključa! Prvo obrišite sve podređene elemente.');
        } else {
          alert(err.status + ' - ' + err.error.message);
        }
      }
    );
  }

}
