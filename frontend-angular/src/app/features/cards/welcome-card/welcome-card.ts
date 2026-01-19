import { Component } from '@angular/core';
import { AuthStateService } from '../../../shared/services/AuthStateService';

@Component({
  selector: 'app-welcome-card',
  standalone: true,
  templateUrl: './welcome-card.html',
  styleUrl: './welcome-card.css'
})
export class WelcomeCard {

  username: string | null = null;

  constructor(private authState: AuthStateService) {
    this.authState.username$.subscribe(name => {
      this.username = name;
    });
  }

  get message() {
    return this.username
      ? `Welcome back, ${this.username}`
      : `Welcome to your dashboard`;
  }
}
