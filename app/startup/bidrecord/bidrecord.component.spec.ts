import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidrecordComponent } from './bidrecord.component';

describe('BidrecordComponent', () => {
  let component: BidrecordComponent;
  let fixture: ComponentFixture<BidrecordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BidrecordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidrecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
