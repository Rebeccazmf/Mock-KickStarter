import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FunderHomeComponent } from './funder-home.component';

describe('FunderHomeComponent', () => {
  let component: FunderHomeComponent;
  let fixture: ComponentFixture<FunderHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FunderHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FunderHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
