import { TestBed, inject } from '@angular/core/testing';

import { BidrecordService } from './bidrecord.service';

describe('BidrecordService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BidrecordService]
    });
  });

  it('should be created', inject([BidrecordService], (service: BidrecordService) => {
    expect(service).toBeTruthy();
  }));
});
