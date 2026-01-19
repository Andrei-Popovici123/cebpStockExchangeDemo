import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankAccountService, BankAccountModel } from '../../../shared/services/BankAccountService';
import { BankAccountCard } from '../bank-account-card/bank-account-card';
import { AuthStateService } from '../../../shared/services/AuthStateService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-card',
  standalone: true,
  imports: [CommonModule, BankAccountCard],
  templateUrl: './account-card.html',
  styleUrl: './account-card.css'
})
export class AccountCard implements OnInit {
  @Input() userId!: number;

  username: string = '';
  bankAccounts: BankAccountModel[] = [];
  loading = true;

  constructor(
    private bankAccountService: BankAccountService,
    private authState: AuthStateService,
    private router: Router
  ) { }

  ngOnInit() {
    this.authState.username$.subscribe(name => {
      this.username = name ?? '';
    });

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

  logout() {
    console.log("Successfully Loggout in as " + this.authState.currentUserName)
    this.authState.logout();
    this.router.navigate(['/']);
  }
}
