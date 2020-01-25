import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFormularComponent } from './user-formular.component';

describe('UserFormularComponent', () => {
  let component: UserFormularComponent;
  let fixture: ComponentFixture<UserFormularComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserFormularComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserFormularComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
