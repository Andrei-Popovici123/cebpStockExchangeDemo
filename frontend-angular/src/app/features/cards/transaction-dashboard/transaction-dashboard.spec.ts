import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionDashboard } from './transaction-dashboard';

describe('TransactionDashboard', () => {
  let component: TransactionDashboard;
  let fixture: ComponentFixture<TransactionDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransactionDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransactionDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
