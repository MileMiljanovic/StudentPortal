import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminManagerService {

  user;
  korisnici = [];
  studenti = [];
  departmani = [];
  nastavnici = [];
  predDomaci = [];
  predStrani = [];
  progDomaci = [];
  progStrani = [];
  formulari = [];
  zamene = [];
  constructor() { }
}
