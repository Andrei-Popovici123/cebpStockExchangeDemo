import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appSettings } from '../../../settings/appSettings';

export interface BankAccountModel {
    id: number;
    currency: string;
    amount: number;
}

@Injectable({ providedIn: 'root' })
export class BankAccountService {
    private baseUrl = appSettings.dotnetDomain + '/api/users';

    constructor(private http: HttpClient) { }

    getUserBankAccounts(userId: number): Observable<BankAccountModel[]> {
        if (appSettings.useMock) {
            return this.http.get<BankAccountModel[]>('/mocks/bank_accounts.json')
        }
        return this.http.get<BankAccountModel[]>(`${this.baseUrl}/${userId}/bankaccounts`);
    }
}
