import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockCatalogCard } from './stock-catalog-card';

describe('StockCatalogCard', () => {
  let component: StockCatalogCard;
  let fixture: ComponentFixture<StockCatalogCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StockCatalogCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockCatalogCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
