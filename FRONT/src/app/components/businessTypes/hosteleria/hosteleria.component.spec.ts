import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HosteleriaComponent } from './hosteleria.component';

describe('HosteleriaComponent', () => {
  let component: HosteleriaComponent;
  let fixture: ComponentFixture<HosteleriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HosteleriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HosteleriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
