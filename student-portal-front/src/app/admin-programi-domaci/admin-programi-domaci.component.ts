import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminManagerService } from '../admin-manager.service';
import { ModalService } from '../_modal';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-programi-domaci',
  templateUrl: './admin-programi-domaci.component.html',
  styleUrls: ['./admin-programi-domaci.component.css']
})
export class AdminProgramiDomaciComponent implements OnInit {

  token;
  programDomaciForm;
  editProgramDomaciForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private adminService: AdminManagerService,
    private modalService: ModalService
  ) {
    this.token = localStorage.getItem('token');
    this.programDomaciForm = this.formBuilder.group({
      naziv: '',
      departman: {},
      sef: {}
    });
    this.editProgramDomaciForm = this.formBuilder.group({
      naziv: '',
      departman: {},
      sef: {}
    });
   }

  ngOnInit() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.get<any>('http://localhost:8080/progDomaci', {headers}).subscribe(
      (data) => {
        this.adminService.progDomaci = data;
      },
      (err) => { alert(err.status + ' - ' + err.error.message); }
    );
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  openWithParamProgramDom(id: string, param) {
    this.modalService.openWithParamProgramDom(id, param);
    this.editProgramDomaciForm.get('naziv').setValue(param.naziv);
    this.editProgramDomaciForm.get('departman').setValue(param.departman);
    this.editProgramDomaciForm.get('sef').setValue(param.sef);
  }

  closeModal(id: string) {
      this.modalService.close(id);
      this.modalService.paramProgramDom = this.modalService.placeholderProgramDom;
      this.programDomaciForm.reset();
      this.editProgramDomaciForm.reset();
  }

  addProgramDom(form) {
    if (!form.naziv) {
      alert('Unesite naziv!');
      return;
    }
    if (!form.departman) {
      alert('Izaberite departman!');
      return;
    }
    if (Object.keys(form.departman).length === 0) {
      alert('Izaberite departman!');
      return;
    }
    if (!form.sef) {
      alert('Izaberite šefa programa!');
      return;
    }
    if (Object.keys(form.sef).length === 0) {
      alert('Izaberite šefa programa!');
      return;
    }
    const request = {
      naziv: form.naziv,
      departman: form.departman,
      sef: form.sef
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.post<any>('http://localhost:8080/progDomaci', request, {headers}).subscribe(
      (data) => {
        this.adminService.progDomaci.push(request);
        alert(form.naziv + ' uspešno dodat!');
        this.programDomaciForm.reset();
        this.adminService.progDomaci.sort((a, b) => (a.naziv > b.naziv) ? 1 : -1);
        this.closeModal('addProgramDomaciModal');
      },
      (err) => {
          alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  editProgramDom(form) {
    const request = {
      naziv: form.naziv,
      departman: form.departman,
      sef: form.sef
    };
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.put<any>('http://localhost:8080/progDomaci/' + form.naziv, request, {headers}).subscribe(
      (data) => {
        alert(form.naziv + ' uspešno izmenjen!');
        this.editProgramDomaciForm.reset();
        const index = this.adminService.progDomaci.findIndex(x => x.naziv === form.naziv);
        this.adminService.progDomaci[index] = request;
        this.closeModal('editProgramDomaciModal');
      },
      (err) => {
        alert(err.status + ' - ' + err.error.message);
      }
    );
  }

  deleteProgramDom(i) {
    const id = this.adminService.progDomaci[i].naziv;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + this.token });
    this.http.delete<any>('http://localhost:8080/progDomaci/' + id, {headers}).subscribe(
      (data) => {
        this.adminService.progDomaci.splice(i, 1);
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
