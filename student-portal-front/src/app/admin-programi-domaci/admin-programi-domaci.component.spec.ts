import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProgramiDomaciComponent } from './admin-programi-domaci.component';

describe('AdminProgramiDomaciComponent', () => {
  let component: AdminProgramiDomaciComponent;
  let fixture: ComponentFixture<AdminProgramiDomaciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProgramiDomaciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProgramiDomaciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
