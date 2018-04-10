import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IdeaoptionComponent } from './ideaoption.component';

describe('IdeaoptionComponent', () => {
  let component: IdeaoptionComponent;
  let fixture: ComponentFixture<IdeaoptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IdeaoptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IdeaoptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
