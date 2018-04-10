import { TestBed, inject } from '@angular/core/testing';

import { CreatorHomeService } from './creator-home.service';

describe('CreatorHomeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreatorHomeService]
    });
  });

  it('should be created', inject([CreatorHomeService], (service: CreatorHomeService) => {
    expect(service).toBeTruthy();
  }));
});
