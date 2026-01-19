import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-information-card',
  imports: [],
  templateUrl: './information-card.html',
  styleUrl: './information-card.css',
})
export class InformationCard {
  @Input() title = '';
}
