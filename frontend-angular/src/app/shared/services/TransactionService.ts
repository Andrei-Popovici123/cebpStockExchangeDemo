import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appSettings } from '../../../settings/appSettings';
import { StockModel } from '../services/StockService';

export interface TransactionModel {
    type: number;       // 0 = buy, 1 = sell
    stock: StockModel;
    userId: number;
    amount: number;
    totalPrice: number;
    id: number;
}

@Injectable({ providedIn: 'root' })
export class TransactionService {
    private baseUrl = appSettings.javaDomain + '/api/transactions';

    constructor(private http: HttpClient) { }

    getUserTransactions(userId: number): Observable<TransactionModel[]> {
        if (appSettings.useMock) {
            return this.http.get<TransactionModel[]>('/mocks/transactions_user.json');
        }

        return this.http.get<TransactionModel[]>(`${this.baseUrl}/user/${userId}`);
    }

    getAllTransactions(): Observable<TransactionModel[]> {
        if (appSettings.useMock) {
            return this.http.get<TransactionModel[]>('/mocks/transactions.json');
        }

        return this.http.get<TransactionModel[]>(this.baseUrl);
    }
}
