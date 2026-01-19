import { Component, Input } from '@angular/core';
import { CandleStickChart, CandleData } from '../../charts/candle-stick-chart/candle-stick-chart';

@Component({
  selector: 'app-stock-card',
  standalone: true,
  imports: [CandleStickChart],
  templateUrl: './stock-card.html',
  styleUrl: './stock-card.css'
})
export class StockCard {
  @Input() title = '';
  @Input() candles: CandleData[] = [];

  buyStock() {
    const amount = prompt(`Enter amount of ${this.title} to BUY:`);

    if (!amount) return;

    alert(
      `Could not "Buy" ${amount} of ${this.title} stocks\nReason: This feature is not implemented yet`
    );
  }

  sellStock() {
    const amount = prompt(`Enter amount of ${this.title} to SELL:`);

    if (!amount) return;

    alert(
      `Could not "Sell" ${amount} of ${this.title} stocks\nReason: This feature is not implemented yet`
    );
  }
}
