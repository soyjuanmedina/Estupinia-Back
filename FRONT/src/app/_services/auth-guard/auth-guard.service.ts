import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { UserService } from '../user.service';


@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public _userService: UserService,
    public router: Router) { }

  canActivate(): boolean {
    if (!this._userService.user) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}