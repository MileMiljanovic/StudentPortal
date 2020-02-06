import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminManagerService {

  user;
  authHeader: HttpHeaders;
  korisnici = [];
  studenti = [];
  departmani = [];
  nastavnici = [];
  predDomaci = [];
  predStrani = [];
  progDomaci = [];
  progStrani = [];
  formulari = [];
  zamDomaci = [];
  zamStrani = [];
  constructor() { }
}
