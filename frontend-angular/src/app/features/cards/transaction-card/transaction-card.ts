import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionModel } from '../../../shared/services/TransactionService';

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
