import { Component } from '@angular/core';
import { TransactionDashboard } from '../../cards/transaction-dashboard/transaction-dashboard';

@Component({
  selector: 'app-transactions',
  imports: [TransactionDashboard],
  templateUrl: './transactions.html',
  styleUrl: './transactions.css',
})
export class Transactions {

}
