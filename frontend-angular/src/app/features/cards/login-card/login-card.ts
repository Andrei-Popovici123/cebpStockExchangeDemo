import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../shared/auth/auth-service';
import { RouterLink } from '@angular/router';
import { AuthStateService } from '../../../shared/services/AuthStateService';
import { Router } from '@angular/router';

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

  constructor(
    private auth: AuthService,
    private authState: AuthStateService,
    private router: Router
  ) { }

  login() {
    this.auth.login({ username: this.username, password: this.password })
      .subscribe({
        next: (user) => {
          this.message = 'Login successful';

          this.authState.login(user.id, user.username);
          console.log("Successfully Logged in as " + user.username)
          this.router.navigate(['/']);
        },
        error: () => this.message = 'Invalid username or password'
      });
  }
}
