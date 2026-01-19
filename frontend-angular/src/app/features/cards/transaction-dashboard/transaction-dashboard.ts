import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionCard } from '../transaction-card/transaction-card';
import { TransactionService, TransactionModel } from '../../../shared/services/TransactionService';

@Component({
  selector: 'app-transaction-dashboard',
  standalone: true,
  imports: [CommonModule, TransactionCard],
  templateUrl: './transaction-dashboard.html',
  styleUrl: './transaction-dashboard.css'
})
export class TransactionDashboard implements OnInit {
  @Input() userId!: number;

  transactions: TransactionModel[] = [];
  loading = true;

  constructor(private transactionService: TransactionService) { }

  ngOnInit() {
    this.transactionService.getUserTransactions(this.userId).subscribe({
      next: txs => {
        this.transactions = txs;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }
}
