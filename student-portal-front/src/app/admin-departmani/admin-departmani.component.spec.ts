import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDepartmaniComponent } from './admin-departmani.component';

describe('AdminDepartmaniComponent', () => {
  let component: AdminDepartmaniComponent;
  let fixture: ComponentFixture<AdminDepartmaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminDepartmaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDepartmaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
