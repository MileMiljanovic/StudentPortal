import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-departmani',
  templateUrl: './admin-departmani.component.html',
  styleUrls: ['./admin-departmani.component.css']
})
export class AdminDepartmaniComponent implements OnInit {

  token;
  departmanForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.departmanForm = this.formBuilder.group({
      naziv: '',
      selectedKoord: {}
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/departmani', {headers}).subscribe(
      (data) => {
        this.adminService.departmani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.departmanForm.reset();
  }

  addDepartman(form) {
    if (!form.naziv) {
      alert('Unesite ime departmana!');
      return;
    }
    if (!form.selectedKoord) {
      alert('Izaberite koordinatora!');
      return;
    }
    if (Object.keys(form.selectedKoord).length === 0) {
      alert('Izaberite koordinatora!');
      return;
    }
    delete form.selectedKoord.password;
    const request = {
      departmanId: form.naziv,
      koordinator: form.selectedKoord
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/departmani', request, {headers}).subscribe(
      (data) => {
        this.adminService.departmani.push(request);
        alert(form.naziv + ' uspešno dodat!');
        this.departmanForm.reset();
        this.adminService.departmani.sort((a, b) => (a.departmanId > b.departmanId) ? 1 : -1);
        this.closeModal('addDepartmanModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deleteDepartman(i) {
    const id = this.adminService.departmani[i].departmanId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/departmani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.departmani.splice(i, 1);
        alert(id + ' uspešno obrisan!');
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
