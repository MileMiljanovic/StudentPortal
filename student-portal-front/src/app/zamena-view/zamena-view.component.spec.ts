import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZamenaViewComponent } from './zamena-view.component';

describe('ZamenaViewComponent', () => {
  let component: ZamenaViewComponent;
  let fixture: ComponentFixture<ZamenaViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZamenaViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZamenaViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
