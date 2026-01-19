import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appSettings } from '../../../settings/appSettings';

export interface StockModel {
    name: string;
    amount: number;
    price: number;
    apreCoef: number;
    id: number;
}

@Injectable({ providedIn: 'root' })
export class StockService {
    private baseUrl = appSettings.javaDomain + '/api/stocks';

    constructor(private http: HttpClient) { }

    getAllStocks(): Observable<StockModel[]> {
        if (appSettings.useMock) {
            return this.http.get<StockModel[]>('/mocks/stocks.json');
        }

        return this.http.get<StockModel[]>(this.baseUrl);
    }
}
