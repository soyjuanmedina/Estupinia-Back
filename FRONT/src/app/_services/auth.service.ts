import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const AUTH_CONTROLLER = '/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_CONTROLLER + 'login', credentials, httpOptions);
  }

  logout(): void {
    this.token.signOut();
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_CONTROLLER + 'register', user, httpOptions);
  }
}