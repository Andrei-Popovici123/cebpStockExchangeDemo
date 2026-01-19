import { Component, OnInit } from '@angular/core';
import { WelcomeCard } from '../welcome-card/welcome-card';
import { PieStockInfoCard } from '../pie-stock-info-card/pie-stock-info-card';
import { CandleStockInfoCard } from '../candle-stock-info-card/candle-stock-info-card';
import { CandleData } from '../../charts/candle-stick-chart/candle-stick-chart';

import { StockService, StockModel } from '../../../shared/services/StockService';
import { TransactionService, TransactionModel } from '../../../shared/services/TransactionService';
import { StockHistoryService, StockHistory } from '../../../shared/services/StockHistoryService';

@Component({
  selector: 'app-home-dashboard',
  standalone: true,
  imports: [WelcomeCard, PieStockInfoCard, CandleStockInfoCard],
  templateUrl: './home-dashboard.html',
  styleUrl: './home-dashboard.css',
})
export class HomeDashboard implements OnInit {

  // PIE 1 – Stocks Available
  pie1Labels: string[] = [];
  pie1Values: number[] = [];

  // PIE 2 – Stock Prices
  pie2Labels: string[] = [];
  pie2Values: number[] = [];

  // PIE 3 – Transactions Made
  pie3Labels: string[] = [];
  pie3Values: number[] = [];

  // Candle chart
  candleTitle = '';
  candleData: CandleData[] = [];

  constructor(
    private stockService: StockService,
    private transactionService: TransactionService,
    private historyService: StockHistoryService
  ) { }

  ngOnInit() {
    this.loadStocks();
    this.loadTransactions();
    this.loadLatestCandle();
  }

  // PIE 1 & PIE 2
  loadStocks() {
    this.stockService.getAllStocks().subscribe(stocks => {
      this.pie1Labels = stocks.map(s => s.name);
      this.pie1Values = stocks.map(s => s.amount);

      this.pie2Labels = stocks.map(s => s.name);
      this.pie2Values = stocks.map(s => s.price);
    });
  }

  // PIE 3 – Transactions per stock
  loadTransactions() {
    this.transactionService.getAllTransactions().subscribe(txs => {
      const grouped: Record<string, number> = {};

      txs.forEach(tx => {
        const name = tx.stock.name;
        grouped[name] = (grouped[name] || 0) + 1;
      });

      this.pie3Labels = Object.keys(grouped);
      this.pie3Values = Object.values(grouped);
    });
  }

  // CANDLE – Most recent stock
  loadLatestCandle() {
    this.historyService.getAll().subscribe(history => {
      if (history.length === 0) return;

      const sorted = [...history].sort(
        (a, b) => new Date(b.timeframe).getTime() - new Date(a.timeframe).getTime()
      );

      const latest = sorted[0];
      const stockName = latest.stock.name;

      this.candleTitle = `${stockName} Latest Candles`;

      const stockHistory = history.filter(h => h.stock.name === stockName);

      const candles = stockHistory
        .sort((a, b) => new Date(a.timeframe).getTime() - new Date(b.timeframe).getTime())
        .slice(-5) // last 5
        .map(h => ({
          x: h.timeframe,
          y: [
            h.price_open,
            h.price_high,
            h.price_low,
            h.price_closed
          ] as [number, number, number, number]
        }));

      this.candleData = candles;
    });
  }
}
