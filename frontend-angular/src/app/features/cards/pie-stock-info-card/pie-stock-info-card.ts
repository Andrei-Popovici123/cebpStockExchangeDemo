import { Component, Input } from '@angular/core';
import { PieChart } from '../../charts/pie-chart/pie-chart';

@Component({
  selector: 'app-pie-stock-info-card',
  standalone: true,
  imports: [PieChart],
  templateUrl: './pie-stock-info-card.html',
  styleUrl: './pie-stock-info-card.css'
})
export class PieStockInfoCard {
  @Input() title = '';
  @Input() labels: string[] = [];
  @Input() values: number[] = [];
}
