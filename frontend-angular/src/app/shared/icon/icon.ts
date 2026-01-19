import { Component, input, computed } from '@angular/core';

@Component({
  selector: 'app-icon',
  standalone: true,
  templateUrl: './icon.html',
  styleUrl: './icon.css'
})
export class Icon {
  name = input.required<string>();
  format = input<'svg' | 'png'>('svg');

  src = computed(() => `/icons/${this.name()}.${this.format()}`);
}
