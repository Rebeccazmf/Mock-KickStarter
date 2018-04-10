import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartupHomeComponent } from './startup-home.component';

describe('StartupHomeComponent', () => {
  let component: StartupHomeComponent;
  let fixture: ComponentFixture<StartupHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartupHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartupHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
