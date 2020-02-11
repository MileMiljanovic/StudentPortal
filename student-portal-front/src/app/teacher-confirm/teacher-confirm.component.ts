import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-teacher-confirm',
  templateUrl: './teacher-confirm.component.html',
  styleUrls: ['./teacher-confirm.component.css']
})
export class TeacherConfirmComponent implements OnInit {

  token;
  student;
  formular;
  zamenaId;
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private spinnerService: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.token = params.get('token');
      this.student = params.get('student');
      this.formular = params.get('formular');
      this.zamenaId = params.get('zamenaId');
    });
  }

  confirm(odg) {
    this.spinnerService.show();
    this.http.get<any>('http://localhost:8080/api/formulari/' + this.formular + '/zamene/' + this.zamenaId + '/' + this.token + '/' + odg,
    {responseType: 'text' as 'json'}).subscribe(
      (data) => {
        this.spinnerService.hide();
        alert('Odgovor uspešno poslat!');
      },
      (err) => {
        this.spinnerService.hide();
        console.log(err);
        alert(err.status + ' - ' + err.error);
      }
    );
  }

}
