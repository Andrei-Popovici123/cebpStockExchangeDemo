import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-welcome-card',
  standalone: true,
  templateUrl: './welcome-card.html',
  styleUrl: './welcome-card.css'
})
export class WelcomeCard {
  @Input() username: string | null = null;

  get message() {
    return this.username
      ? `Welcome back, ${this.username}`
      : `Welcome to your dashboard`;
  }
}
