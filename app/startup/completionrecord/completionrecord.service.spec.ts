import { TestBed, inject } from '@angular/core/testing';

import { CompletionrecordService } from './completionrecord.service';

describe('CompletionrecordService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CompletionrecordService]
    });
  });

  it('should be created', inject([CompletionrecordService], (service: CompletionrecordService) => {
    expect(service).toBeTruthy();
  }));
});
