import { Component } from '@angular/core';
import { AccountCard } from '../../cards/account-card/account-card';

@Component({
  selector: 'app-account',
  imports: [AccountCard],
  templateUrl: './account.html',
  styleUrl: './account.css',
})
export class Account {

}
