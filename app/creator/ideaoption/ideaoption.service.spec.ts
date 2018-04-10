import { TestBed, inject } from '@angular/core/testing';

import { IdeaoptionService } from './ideaoption.service';

describe('IdeaoptionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IdeaoptionService]
    });
  });

  it('should be created', inject([IdeaoptionService], (service: IdeaoptionService) => {
    expect(service).toBeTruthy();
  }));
});
