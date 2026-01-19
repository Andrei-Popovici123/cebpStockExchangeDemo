import { Component, Input, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'app-line-chart',
  standalone: true,
  imports: [BaseChartDirective],
  template: `
    <canvas baseChart
      [data]="lineChartData"
      [options]="lineChartOptions"
      chartType="line">
    </canvas>
  `
})
export class LineChart implements OnChanges {
  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  @Input() labels: string[] = [];
  @Input() values: number[] = [];
  @Input() color: string = '#4bc0c0';

  lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Price',
        borderColor: this.color,
        backgroundColor: 'transparent',
        fill: false,
        tension: 0.3
      }
    ]
  };

  lineChartOptions: ChartConfiguration<'line'>['options'] = {
    responsive: true,
    animation: false,
    scales: {
      x: { display: false },
      y: { display: true }
    }
  };

  ngOnChanges(changes: SimpleChanges) {
    this.lineChartData.labels = [...this.labels];
    this.lineChartData.datasets[0].data = [...this.values];
    this.lineChartData.datasets[0].borderColor = this.color;

    this.chart?.update();
  }
}
