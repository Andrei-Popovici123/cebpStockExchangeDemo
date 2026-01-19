import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { Icon } from '../../shared/icon/icon';
import { AuthStateService } from '../../shared/services/AuthStateService';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, Icon],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  protected readonly isDark = signal(false);

  loggedIn = false;

  constructor(private authState: AuthStateService, private router: Router) { }

  ngOnInit() {
    this.authState.loggedIn$.subscribe(state => {
      this.loggedIn = state;
    });
  }

  handleTransactionsClick() {
    if (!this.loggedIn) {
      alert('You must be logged in to view your transactions.');
      return;
    }
    this.router.navigate(['/transactions']);
  }


  toggleDarkMode() {
    this.isDark.update(v => !v);

    const html = document.documentElement;
    console.log('Root element:', html);

    if (this.isDark()) {
      html.classList.add('dark-theme');
      console.log("Switched to dark")
    } else {
      html.classList.remove('dark-theme');
      console.log("Switched to light")
    }

    console.log('Classes now:', html.className);
  }
}
