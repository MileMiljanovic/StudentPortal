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
  formular;
  constructor() { }
}
