import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Icon } from '../../shared/icon/icon';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, Icon],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  protected readonly isDark = signal(false);

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
