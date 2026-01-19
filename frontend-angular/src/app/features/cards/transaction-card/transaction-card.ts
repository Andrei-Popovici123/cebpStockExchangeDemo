import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface TransactionModel {
  type: number;       // 0 = buy, 1 = sell
  stock: string;      // stock name
  userId: number;
  amount: number;
  totalPrice: number;
  id: number;
}

@Component({
  selector: 'app-transaction-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './transaction-card.html',
  styleUrl: './transaction-card.css'
})
export class TransactionCard {
  @Input() transaction!: TransactionModel;

  get typeLabel() {
    return this.transaction.type === 0 ? 'BOUGHT' : 'SOLD';
  }
}
