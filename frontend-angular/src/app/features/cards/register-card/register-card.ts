import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../shared/auth/auth-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register-card',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register-card.html',
  styleUrl: './register-card.css'
})
export class RegisterCard {
  username = '';
  password = '';
  message = '';

  constructor(private auth: AuthService) { }

  register() {
    this.auth.register({ username: this.username, password: this.password })
      .subscribe({
        next: () => this.message = 'Registration successful',
        error: () => this.message = 'Registration failed'
      });
  }
}
