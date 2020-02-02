import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-predmeti-strani',
  templateUrl: './admin-predmeti-strani.component.html',
  styleUrls: ['./admin-predmeti-strani.component.css']
})
export class AdminPredmetiStraniComponent implements OnInit {

  token;
  predmetStraniForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.predmetStraniForm = this.formBuilder.group({
      predmetId: '',
      naziv: '',
      program: {},
      espb: ''
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/predStrani', {headers}).subscribe(
      (data) => {
        this.adminService.predStrani = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.predmetStraniForm.reset();
  }

  addPredmetStr(form) {
    if (!form.predmetId) {
      alert('Unesite ID predmeta!');
      return;
    }
    if (!form.naziv) {
      alert('Unesite naziv!');
      return;
    }
    if (!form.espb) {
      alert('Unesite ESPB bodove!');
      return;
    }
    if (isNaN(form.espb)) {
      alert('ESPB bodovi moraju biti broj!');
      return;
    }
    if (!form.program) {
      alert('Izaberite studijski program!');
      return;
    }
    if (Object.keys(form.program).length === 0) {
      alert('Izaberite studijski program!');
      return;
    }
    const espbParsed = +form.espb;
    const request = {
      predmetId: form.predmetId,
      naziv: form.naziv,
      program: form.program,
      espb: espbParsed
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/predStrani', request, {headers}).subscribe(
      (data) => {
        this.adminService.predStrani.push(request);
        alert(form.predmetId + ' uspešno dodat!');
        this.predmetStraniForm.reset();
        this.adminService.predStrani.sort((a, b) => (a.predmetId > b.predmetId) ? 1 : -1);
        this.closeModal('addPredmetStraniModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deletePredmetStr(i) {
    const id = this.adminService.predStrani[i].predmetId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/predStrani/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.predStrani.splice(i, 1);
        this.adminService.formulari.forEach(f => {
          f.zamene = f.zamene.filter(x => x.predmetStrani.predmetId !== id);
        });
        alert('Predmet ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
