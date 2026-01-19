import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PieStockInfoCard } from './pie-stock-info-card';

describe('PieStockInfoCard', () => {
  let component: PieStockInfoCard;
  let fixture: ComponentFixture<PieStockInfoCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PieStockInfoCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PieStockInfoCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
