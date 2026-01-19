import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankAccountService, BankAccountModel } from '../../../shared/services/BankAccountService';
import { BankAccountCard } from '../bank-account-card/bank-account-card';

@Component({
  selector: 'app-account-card',
  standalone: true,
  imports: [CommonModule, BankAccountCard],
  templateUrl: './account-card.html',
  styleUrl: './account-card.css'
})
export class AccountCard implements OnInit {
  @Input() username: string = '';
  @Input() userId!: number;

  bankAccounts: BankAccountModel[] = [];
  loading = true;

  constructor(private bankAccountService: BankAccountService) { }

  ngOnInit() {
    this.bankAccountService.getUserBankAccounts(this.userId).subscribe({
      next: accounts => {
        this.bankAccounts = accounts;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }
}
