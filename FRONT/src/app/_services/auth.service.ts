import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { ProjectService } from './project.service';

const AUTH_CONTROLLER = '/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private token: TokenStorageService, private _projectService: ProjectService) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_CONTROLLER + 'login', credentials, httpOptions);
  }

  logout(): void {
    this.token.signOut();
    this._projectService.step = 3;
    delete this._projectService.project.type;
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_CONTROLLER + 'register', user, httpOptions);
  }
}