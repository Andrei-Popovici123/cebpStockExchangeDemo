import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankAccountCard } from './bank-account-card';

describe('BankAccountCard', () => {
  let component: BankAccountCard;
  let fixture: ComponentFixture<BankAccountCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankAccountCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankAccountCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
