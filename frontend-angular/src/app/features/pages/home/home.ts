import { Component } from '@angular/core';
import { HomeDashboard } from '../../cards/home-dashboard/home-dashboard';

@Component({
  selector: 'app-home',
  imports: [HomeDashboard],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
