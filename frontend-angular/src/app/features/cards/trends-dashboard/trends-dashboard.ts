import { Component, OnInit } from '@angular/core';
import { CandleStockInfoCard } from '../candle-stock-info-card/candle-stock-info-card';
import { CandleData } from '../../charts/candle-stick-chart/candle-stick-chart';
import { StockHistoryService, StockHistory } from '../../../shared/services/StockHistoryService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-trends-dashboard',
  standalone: true,
  imports: [CandleStockInfoCard, CommonModule],
  templateUrl: './trends-dashboard.html',
  styleUrl: './trends-dashboard.css'
})
export class TrendsDashboard implements OnInit {

  trends: { stock: string; candles: CandleData[] }[] = [];
  loading = true;

  constructor(private stockHistory: StockHistoryService) { }

  ngOnInit() {
    this.stockHistory.getAll().subscribe(history => {
      const grouped = this.groupByStock(history);

      this.trends = Object.keys(grouped).map(stockName => ({
        stock: stockName,
        candles: this.toCandleData(grouped[stockName])
      }));

      this.loading = false;
    });
  }

  private groupByStock(history: StockHistory[]) {
    return history.reduce((acc, entry) => {
      if (!acc[entry.stock.name]) acc[entry.stock.name] = [];
      acc[entry.stock.name].push(entry);
      return acc;
    }, {} as Record<string, StockHistory[]>);
  }

  private toCandleData(history: StockHistory[]): CandleData[] {
    return history.sort((a, b) => new Date(b.timeframe).getTime() - new Date(a.timeframe).getTime()) // newest first 
      .reverse() // oldest → newest for chart 
      .map(h => ({
        x: new Date(h.timeframe),
        y: [
          h.price_open,
          h.price_high,
          h.price_low,
          h.price_closed
        ] as [number, number, number, number]
      }));
  }
}
