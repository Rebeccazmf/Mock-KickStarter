import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcompletionComponent } from './viewcompletion.component';

describe('ViewcompletionComponent', () => {
  let component: ViewcompletionComponent;
  let fixture: ComponentFixture<ViewcompletionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewcompletionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcompletionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
