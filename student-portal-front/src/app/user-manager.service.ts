import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserManagerService {

  user;
  authHeader: HttpHeaders;
  formulari = [];
  constructor() {
  }
}
