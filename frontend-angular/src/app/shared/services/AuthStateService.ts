import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthStateService {

    // Global reactive variables
    private loggedInSubject = new BehaviorSubject<boolean>(false);
    private userIdSubject = new BehaviorSubject<number | null>(null);
    private usernameSubject = new BehaviorSubject<string | null>(null);

    // Exposed as Observables for components
    loggedIn$ = this.loggedInSubject.asObservable();
    userId$ = this.userIdSubject.asObservable();
    username$ = this.usernameSubject.asObservable();

    // Direct getters (optional)
    get loggedIn() { return this.loggedInSubject.value; }
    get currentUserId() { return this.userIdSubject.value; }
    get currentUserName() { return this.usernameSubject.value; }

    // Login simulation
    login(userId: number, username: string) {
        this.loggedInSubject.next(true);
        this.userIdSubject.next(userId);
        this.usernameSubject.next(username);
    }

    // Logout simulation
    logout() {
        this.loggedInSubject.next(false);
        this.userIdSubject.next(null);
        this.usernameSubject.next(null);
    }
}
