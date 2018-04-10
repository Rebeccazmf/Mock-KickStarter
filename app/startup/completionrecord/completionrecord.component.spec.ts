import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletionrecordComponent } from './completionrecord.component';

describe('CompletionrecordComponent', () => {
  let component: CompletionrecordComponent;
  let fixture: ComponentFixture<CompletionrecordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletionrecordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletionrecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
