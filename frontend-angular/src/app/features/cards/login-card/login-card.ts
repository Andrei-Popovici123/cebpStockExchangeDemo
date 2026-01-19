import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../shared/auth/auth-service';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-login-card',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login-card.html',
  styleUrl: './login-card.css'
})
export class LoginCard {
  username = '';
  password = '';
  message = '';

  constructor(private auth: AuthService) { }

  login() {
    this.auth.login({ username: this.username, password: this.password })
      .subscribe({
        next: () => this.message = 'Login successful',
        error: () => this.message = 'Invalid username or password'
      });
  }
}
