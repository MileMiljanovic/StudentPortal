import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherConfirmComponent } from './teacher-confirm.component';

describe('TeacherConfirmComponent', () => {
  let component: TeacherConfirmComponent;
  let fixture: ComponentFixture<TeacherConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
