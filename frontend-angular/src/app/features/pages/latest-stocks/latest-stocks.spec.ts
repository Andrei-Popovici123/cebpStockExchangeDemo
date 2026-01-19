import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LatestStocks } from './latest-stocks';

describe('LatestStocks', () => {
  let component: LatestStocks;
  let fixture: ComponentFixture<LatestStocks>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LatestStocks]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LatestStocks);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
