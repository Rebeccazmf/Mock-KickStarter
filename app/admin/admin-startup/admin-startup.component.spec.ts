import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStartupComponent } from './admin-startup.component';

describe('AdminStartupComponent', () => {
  let component: AdminStartupComponent;
  let fixture: ComponentFixture<AdminStartupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminStartupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminStartupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
