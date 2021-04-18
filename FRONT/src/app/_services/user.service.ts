import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';
import { ProjectService } from './project.service';
import { Project } from '../interfaces/project';
import { environment } from '../../environments/environment';

const USER_CONTROLLER = environment.baseUrl + '/user/';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  user: User;
  error: string;

  constructor(private http: HttpClient, private _projectService: ProjectService) {
    if (typeof sessionStorage.getItem('auth-user') !== 'undefined') {
      this.user = JSON.parse(sessionStorage.getItem('auth-user'));
    }
  }

  getUser() {
    return this.http.post(USER_CONTROLLER + 'get', "").subscribe(
      data => {
        this.user = data as User;
        window.sessionStorage.removeItem(USER_KEY);
        window.sessionStorage.setItem(USER_KEY, JSON.stringify(this.user));
      },
      err => {
        this.error = err.error.message;
      }
    );;
  }

  saveUser(user: User) {
    return this.http.post(USER_CONTROLLER + 'save', user).subscribe(
      data => {
        this.user = data as User;
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  saveProjectToUser(project: Project) {
    return this.http.post(USER_CONTROLLER + 'save/project', project).subscribe(
      data => {
        this.getUser();
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  deleteProjectFromUser(project: Project) {
    return this.http.post(USER_CONTROLLER + 'delete/project', project).subscribe(
      data => {
        this.getUser();
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  getPublicContent(): Observable<any> {
    return this.http.get(USER_CONTROLLER + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(USER_CONTROLLER + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(USER_CONTROLLER + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(USER_CONTROLLER + 'admin', { responseType: 'text' });
  }
}