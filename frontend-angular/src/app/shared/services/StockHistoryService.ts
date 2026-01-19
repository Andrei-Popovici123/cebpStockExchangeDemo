import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appSettings } from '../../../settings/appSettings';
import { StockService, StockModel } from './StockService';

export interface StockHistory {
    id: number;
    price_open: number;
    price_high: number;
    price_low: number;
    price_closed: number;
    units_traded: number;
    timeframe: string;
    stock: StockModel;
}

@Injectable({ providedIn: 'root' })
export class StockHistoryService {
    private baseUrl = appSettings.javaDomain + '/api/stock_history';

    constructor(private http: HttpClient) { }

    getAll(): Observable<StockHistory[]> {
        if (appSettings.useMock) {
            return this.http.get<StockHistory[]>('/mocks/stock_history.json');
        }
        return this.http.get<StockHistory[]>(this.baseUrl);
    }
}
