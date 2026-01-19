import { Component, Input, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
  ChartComponent
} from 'ng-apexcharts';
import { NgApexchartsModule } from 'ng-apexcharts'


export type CandleData = {
  x: string | Date;
  y: [number, number, number, number]; // open, high, low, close
};

@Component({
  selector: 'app-candle-stick-chart',
  standalone: true,
  imports: [NgApexchartsModule],
  template: `
    <apx-chart
      #chart
      [series]="series"
      [chart]="chartOptions"
      [xaxis]="xaxis">
    </apx-chart>
  `
})
export class CandleStickChart implements OnChanges {
  @ViewChild('chart') chart?: ChartComponent;

  @Input() data: CandleData[] = [];
  @Input() title: string = 'Candlestick';

  series: ApexAxisChartSeries = [
    {
      name: 'Price',
      data: []
    }
  ];

  chartOptions: ApexChart = {
    type: 'candlestick',
    height: 350
  };

  xaxis: ApexXAxis = {
    type: 'datetime'
  };

  ngOnChanges(changes: SimpleChanges) {
    this.series = [
      {
        name: 'Price',
        data: [...this.data]
      }
    ];
  }
}
