import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStudentiComponent } from './admin-studenti.component';

describe('AdminStudentiComponent', () => {
  let component: AdminStudentiComponent;
  let fixture: ComponentFixture<AdminStudentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminStudentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminStudentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
