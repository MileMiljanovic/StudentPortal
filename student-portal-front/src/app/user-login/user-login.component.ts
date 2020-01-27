import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserManagerService } from '../user-manager.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginForm;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserManagerService
  ) {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
   }

  ngOnInit() {
  }

  onSubmit(login) {
    this.http.post<any>('http://localhost:8080/userLogin', login).subscribe(
      (data) => {
        this.userService.user = data;
        this.router.navigate(['/userIndex']);
      },
      (err) => { alert('Neuspešan login!'); }
    );
  }

}
