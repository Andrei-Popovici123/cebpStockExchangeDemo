import { Component, Input } from '@angular/core';
import { CandleStickChart, CandleData } from '../../charts/candle-stick-chart/candle-stick-chart';

@Component({
  selector: 'app-candle-stock-info-card',
  standalone: true,
  imports: [CandleStickChart],
  templateUrl: './candle-stock-info-card.html',
  styleUrl: './candle-stock-info-card.css'
})
export class CandleStockInfoCard {
  @Input() title = '';
  @Input() data: CandleData[] = [];
}
