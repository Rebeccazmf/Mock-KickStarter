import { TestBed, inject } from '@angular/core/testing';

import { AdminIdeaService } from './admin-idea.service';

describe('AdminIdeaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminIdeaService]
    });
  });

  it('should be created', inject([AdminIdeaService], (service: AdminIdeaService) => {
    expect(service).toBeTruthy();
  }));
});
