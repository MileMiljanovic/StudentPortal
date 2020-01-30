import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPredmetiStraniComponent } from './admin-predmeti-strani.component';

describe('AdminPredmetiStraniComponent', () => {
  let component: AdminPredmetiStraniComponent;
  let fixture: ComponentFixture<AdminPredmetiStraniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPredmetiStraniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPredmetiStraniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
