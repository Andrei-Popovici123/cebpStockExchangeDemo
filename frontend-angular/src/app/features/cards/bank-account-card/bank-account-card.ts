import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-bank-account-card',
  standalone: true,
  templateUrl: './bank-account-card.html',
  styleUrl: './bank-account-card.css'
})
export class BankAccountCard {
  @Input() currency: string = 'USD';
  @Input() amount: number = 0;

  changeAmount() {
    // alert() does not return a value, so we use prompt() instead
    const newValue = prompt("Enter new amount:", this.amount.toString());

    if (newValue !== null && !isNaN(Number(newValue))) {
      this.amount = Number(newValue);
    }
  }

  get title() {
    return `$${this.currency} Bank Account`;
  }
}
