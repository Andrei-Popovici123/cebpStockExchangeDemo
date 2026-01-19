import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrendsDashboard } from './trends-dashboard';

describe('TrendsDashboard', () => {
  let component: TrendsDashboard;
  let fixture: ComponentFixture<TrendsDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrendsDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrendsDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
