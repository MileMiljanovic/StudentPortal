import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminZameneComponent } from './admin-zamene.component';

describe('AdminZameneComponent', () => {
  let component: AdminZameneComponent;
  let fixture: ComponentFixture<AdminZameneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminZameneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminZameneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
