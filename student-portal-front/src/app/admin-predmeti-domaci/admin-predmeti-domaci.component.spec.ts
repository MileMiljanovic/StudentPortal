import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPredmetiDomaciComponent } from './admin-predmeti-domaci.component';

describe('AdminPredmetiDomaciComponent', () => {
  let component: AdminPredmetiDomaciComponent;
  let fixture: ComponentFixture<AdminPredmetiDomaciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPredmetiDomaciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPredmetiDomaciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
