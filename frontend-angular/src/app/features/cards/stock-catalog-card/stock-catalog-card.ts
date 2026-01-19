import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StockCard } from '../stock-card/stock-card';

@Component({
  selector: 'stock-catalog-card',
  standalone: true,
  imports: [CommonModule, StockCard],
  templateUrl: './stock-catalog-card.html',
  styleUrl: './stock-catalog-card.css'
})
export class StockCatalogCard {
  @Input() stocks: any[] = [];
}
