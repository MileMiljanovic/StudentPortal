import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-predmeti-domaci',
  templateUrl: './admin-predmeti-domaci.component.html',
  styleUrls: ['./admin-predmeti-domaci.component.css']
})
export class AdminPredmetiDomaciComponent implements OnInit {

  token;
  predmetDomaciForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.predmetDomaciForm = this.formBuilder.group({
      predmetId: '',
      naziv: '',
      program: {},
      espb: '',
      nastavnik: {}
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/predDomaci', {headers}).subscribe(
      (data) => {
        this.adminService.predDomaci = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.predmetDomaciForm.reset();
  }

  addPredmetDom(form) {
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
    if (!form.nastavnik) {
      alert('Izaberite nastavnika!');
      return;
    }
    if (Object.keys(form.nastavnik).length === 0) {
      alert('Izaberite nastavnika!');
      return;
    }
    const espbParsed = +form.espb;
    const request = {
      predmetId: form.predmetId,
      naziv: form.naziv,
      program: form.program,
      espb: espbParsed,
      nastavnik: form.nastavnik
    };
    console.log(request);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/predDomaci', request, {headers}).subscribe(
      (data) => {
        this.adminService.predDomaci.push(request);
        alert(form.predmetId + ' uspešno dodat!');
        this.predmetDomaciForm.reset();
        this.adminService.predDomaci.sort((a, b) => (a.predmetId > b.predmetId) ? 1 : -1);
        this.closeModal('addPredmetDomaciModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deletePredmetDom(i) {
    const id = this.adminService.predDomaci[i].predmetId;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/predDomaci/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.predDomaci.splice(i, 1);
        this.adminService.formulari.forEach(f => {
          f.zamene = f.zamene.filter(x => x.predmetDomaci.predmetId !== id);
        });
        alert('Predmet ' + id + ' uspešno obrisan!');
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

}
