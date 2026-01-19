import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appSettings } from '../../../settings/appSettings';
import { map } from 'rxjs/operators';

export interface AuthRequest {
    username: string;
    password: string;
}

export interface UserModel {
    id: number;
    username: string;
    password: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
    private baseUrl = '/api/Auth';

    constructor(private http: HttpClient) { }

    register(body: AuthRequest): Observable<any> {
        return this.http.post(`${this.baseUrl}/register`, body);
    }

    login(data: AuthRequest): Observable<UserModel> {

        if (appSettings.useMock) {
            return this.http.get<UserModel[]>('/mocks/current_user.json').pipe(
                map(users => users[0]) // return the first user
            );
        }

        return this.http.post<UserModel>(`${this.baseUrl}/login`, data);
    }

}

