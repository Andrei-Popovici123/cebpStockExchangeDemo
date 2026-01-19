import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-found-card',
  standalone: true,
  templateUrl: './not-found-card.html',
  styleUrl: './not-found-card.css'
})
export class NotFoundCard {

  constructor(private router: Router) { }

  goHome() {
    this.router.navigate(['/']);
  }
}
