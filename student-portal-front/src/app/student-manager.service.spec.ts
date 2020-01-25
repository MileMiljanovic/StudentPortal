import { TestBed } from '@angular/core/testing';

import { StudentManagerService } from './student-manager.service';

describe('StudentManagerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StudentManagerService = TestBed.get(StudentManagerService);
    expect(service).toBeTruthy();
  });
});
