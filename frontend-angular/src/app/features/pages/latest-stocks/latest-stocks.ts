import { Component } from '@angular/core';
import { TrendsDashboard } from '../../cards/trends-dashboard/trends-dashboard';

@Component({
  selector: 'app-latest-stocks',
  imports: [TrendsDashboard],
  templateUrl: './latest-stocks.html',
  styleUrl: './latest-stocks.css',
})
export class LatestStocks {

}
