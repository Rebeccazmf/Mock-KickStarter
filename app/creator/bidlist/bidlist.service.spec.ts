import { TestBed, inject } from '@angular/core/testing';

import { BidlistService } from './bidlist.service';

describe('BidlistService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BidlistService]
    });
  });

  it('should be created', inject([BidlistService], (service: BidlistService) => {
    expect(service).toBeTruthy();
  }));
});
