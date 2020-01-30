import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNastavniciComponent } from './admin-nastavnici.component';

describe('AdminNastavniciComponent', () => {
  let component: AdminNastavniciComponent;
  let fixture: ComponentFixture<AdminNastavniciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminNastavniciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNastavniciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
