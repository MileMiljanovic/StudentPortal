import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProgramiStraniComponent } from './admin-programi-strani.component';

describe('AdminProgramiStraniComponent', () => {
  let component: AdminProgramiStraniComponent;
  let fixture: ComponentFixture<AdminProgramiStraniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProgramiStraniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProgramiStraniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
