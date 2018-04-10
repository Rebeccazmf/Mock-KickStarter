import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartupserviceComponent } from './startupservice.component';

describe('StartupserviceComponent', () => {
  let component: StartupserviceComponent;
  let fixture: ComponentFixture<StartupserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartupserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartupserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
