import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseProgramComponent } from './choose-program.component';

describe('ChooseProgramComponent', () => {
  let component: ChooseProgramComponent;
  let fixture: ComponentFixture<ChooseProgramComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseProgramComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
