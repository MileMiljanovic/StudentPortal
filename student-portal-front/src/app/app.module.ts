import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentIndexComponent } from './student-index/student-index.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { StartComponent } from './start/start.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { StudentMainComponent } from './student-main/student-main.component';
import { ChooseProgramComponent } from './choose-program/choose-program.component';
import { ZamenaComponent } from './zamena/zamena.component';
import { ZamenaViewComponent } from './zamena-view/zamena-view.component';
import { UserIndexComponent } from './user-index/user-index.component';
import { UserFormularComponent } from './user-formular/user-formular.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentIndexComponent,
    UserLoginComponent,
    StartComponent,
    StudentMainComponent,
    ChooseProgramComponent,
    ZamenaComponent,
    ZamenaViewComponent,
    UserIndexComponent,
    UserFormularComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: '', component: StartComponent },
      { path: 'studentIndex', component: StudentIndexComponent },
      { path: 'loginPage', component: UserLoginComponent },
      { path: 'studentMainPage', component: StudentMainComponent },
      { path: 'chooseProgram', component: ChooseProgramComponent },
      { path: 'zamene', component: ZamenaComponent },
      { path: 'zameneView', component: ZamenaViewComponent },
      { path: 'userIndex', component: UserIndexComponent },
      { path: 'userFormular', component: UserFormularComponent }
    ]),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
