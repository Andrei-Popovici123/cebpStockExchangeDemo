import { Component, OnInit } from '@angular/core';
import { StockCatalogCard } from '../../cards/stock-catalog-card/stock-catalog-card';
import { StockHistoryService, StockHistory } from '../../../shared/services/StockHistoryService';

@Component({
  selector: 'app-stocks',
  standalone: true,
  imports: [StockCatalogCard],
  templateUrl: './stocks.html',
  styleUrl: './stocks.css'
})
export class Stocks implements OnInit {

  stocks: any[] = []; // { name: string, candles: CandleData[] }

  constructor(private stockHistory: StockHistoryService) { }

  ngOnInit() {
    this.stockHistory.getAll().subscribe(history => {
      const grouped = this.groupByStock(history);

      this.stocks = Object.keys(grouped).map(stockName => ({
        name: stockName,
        candles: this.toCandleData(grouped[stockName])
      }));
    });
  }

  groupByStock(history: StockHistory[]) {
    return history.reduce((acc, entry) => {
      const name = entry.stock;
      if (!acc[name]) acc[name] = [];
      acc[name].push(entry);
      return acc;
    }, {} as Record<string, StockHistory[]>);
  }

  toCandleData(history: StockHistory[]) {
    return history.sort((a, b) => new Date(b.timeframe).getTime() - new Date(a.timeframe).getTime()) // newest first 
      .slice(0, 5) // take 5 most recent 
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
