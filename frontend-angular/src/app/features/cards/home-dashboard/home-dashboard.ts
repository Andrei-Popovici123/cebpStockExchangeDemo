import { Component } from '@angular/core';
import { WelcomeCard } from '../welcome-card/welcome-card';
import { PieStockInfoCard } from '../pie-stock-info-card/pie-stock-info-card';
import { CandleStockInfoCard } from '../candle-stock-info-card/candle-stock-info-card';
import { CandleData } from '../../charts/candle-stick-chart/candle-stick-chart';

@Component({
  selector: 'app-home-dashboard',
  imports: [WelcomeCard, PieStockInfoCard, CandleStockInfoCard],
  templateUrl: './home-dashboard.html',
  styleUrl: './home-dashboard.css',
})
export class HomeDashboard {
  username: string | null = 'TestUser'; // or null if not found
  pie1Labels = ['AAPL', 'TSLA', 'NVDA'];
  pie1Values = [40, 30, 30];
  pie2Labels = ['AAPL', 'TSLA', 'NVDA'];
  pie2Values = [50, 20, 30];
  candleData: CandleData[] = [
    { x: '2024-01-01', y: [150, 160, 148, 158] as [number, number, number, number] },
    { x: '2024-01-02', y: [158, 165, 155, 162] as [number, number, number, number] },
    { x: '2024-01-03', y: [162, 170, 160, 168] as [number, number, number, number] }
  ];

}
