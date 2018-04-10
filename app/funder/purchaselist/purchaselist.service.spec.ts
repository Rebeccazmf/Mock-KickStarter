import { TestBed, inject } from '@angular/core/testing';

import { PurchaselistService } from './purchaselist.service';

describe('PurchaselistService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PurchaselistService]
    });
  });

  it('should be created', inject([PurchaselistService], (service: PurchaselistService) => {
    expect(service).toBeTruthy();
  }));
});
