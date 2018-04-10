import { TestBed, inject } from '@angular/core/testing';

import { ViewcompletionService } from './viewcompletion.service';

describe('ViewcompletionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewcompletionService]
    });
  });

  it('should be created', inject([ViewcompletionService], (service: ViewcompletionService) => {
    expect(service).toBeTruthy();
  }));
});
