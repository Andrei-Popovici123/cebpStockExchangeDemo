import { Routes } from '@angular/router';

import { Home } from './features/pages/home/home';
import { Stocks } from './features/pages/stocks/stocks';
import { LatestStocks } from './features/pages/latest-stocks/latest-stocks';

import { Account } from './features/pages/account/account';
import { Register } from './features/pages/register/register';
import { Login } from './features/pages/login/login';

import { Transactions } from './features/pages/transactions/transactions';

import { Contact } from './features/pages/contact/contact';
import { Terms } from './features/pages/terms/terms';

import { NotFound } from './features/pages/not-found/not-found';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'home', component: Home },
  { path: 'stocks', component: Stocks },
  { path: 'trends', component: LatestStocks },

  { path: 'account', component: Account },
  { path: 'register', component: Register },
  { path: 'login', component: Login },

  { path: 'transactions', component: Transactions },

  { path: 'contact', component: Contact },
  { path: 'terms', component: Terms },

  { path: '**', component: NotFound }
];
