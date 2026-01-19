import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandleStockInfoCard } from './candle-stock-info-card';

describe('CandleStockInfoCard', () => {
  let component: CandleStockInfoCard;
  let fixture: ComponentFixture<CandleStockInfoCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandleStockInfoCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandleStockInfoCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
