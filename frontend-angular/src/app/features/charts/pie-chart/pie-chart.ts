import { Component, Input, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import { NgApexchartsModule } from 'ng-apexcharts'
import {
  ApexNonAxisChartSeries,
  ApexChart,
  ApexResponsive,
  ApexLegend,
  ChartComponent
} from 'ng-apexcharts';

export type PieChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  labels: string[];
  responsive: ApexResponsive[];
  legend: ApexLegend;
};

@Component({
  selector: 'app-pie-chart',
  standalone: true,
  imports: [NgApexchartsModule],
  template: `
    <apx-chart
      [series]="chartOptions.series"
      [chart]="chartOptions.chart"
      [labels]="chartOptions.labels"
      [responsive]="chartOptions.responsive"
      [legend]="chartOptions.legend">
    </apx-chart>
  `
})
export class PieChart implements OnChanges {
  @ViewChild('chart') chart?: ChartComponent;

  @Input() labels: string[] = [];
  @Input() values: number[] = [];

  chartOptions: PieChartOptions = {
    series: [],
    chart: {
      type: 'pie',
      height: 350
    },
    labels: [],
    legend: {
      position: 'bottom'
    },
    responsive: [
      {
        breakpoint: 480,
        options: {
          chart: { width: 450 },
          legend: { position: 'bottom' }
        }
      }
    ]
  };

  ngOnChanges(changes: SimpleChanges) {
    this.chartOptions.series = [...this.values];
    this.chartOptions.labels = [...this.labels];
  }
}
