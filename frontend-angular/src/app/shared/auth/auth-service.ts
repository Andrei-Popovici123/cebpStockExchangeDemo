import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AuthRequest {
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

    login(body: AuthRequest): Observable<any> {
        return this.http.post(`${this.baseUrl}/login`, body);
    }
}
