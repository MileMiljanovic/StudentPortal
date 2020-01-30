import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFormulariComponent } from './admin-formulari.component';

describe('AdminFormulariComponent', () => {
  let component: AdminFormulariComponent;
  let fixture: ComponentFixture<AdminFormulariComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminFormulariComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFormulariComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
