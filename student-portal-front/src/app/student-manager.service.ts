import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentManagerService {

  student;
  programiStrani = [];
  izabraniProgram;
  predmetiDomaci = [];
  predmetiStrani = [];
  formularId;
  constructor() { }

  /*getStudent() {
    return this.student;
  }

  getProgramiStrani() {
    return this.programiStrani;
  }

  getIzabraniProgram() {
    return this.izabraniProgram;
  }*/
}
