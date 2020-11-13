import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';
import { ProjectService } from './project.service';
import { Project } from '../interfaces/project';

const USER_CONTROLLER = '/user/';
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
    return this.http.post('/user/get', "").subscribe(
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
    console.log('dentro', user);
    return this.http.post('/user/save', user).subscribe(
      data => {
        console.log(data);
        this.user = data as User;
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  saveProjectToUser(project: Project) {
    return this.http.post('/user/save/project', project).subscribe(
      data => {
        this.getUser();
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  deleteProjectFromUser(project: Project) {
    return this.http.post('/user/delete/project', project).subscribe(
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