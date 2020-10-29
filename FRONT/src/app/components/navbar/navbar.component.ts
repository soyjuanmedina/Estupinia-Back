import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { TokenStorageService } from '../../_services/token-storage.service';
import { UserService } from '../../_services/user.service';
declare var $: any;

@Component({
  selector: 'navbar-component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  loginForm: any = {};
  loginErrorMessage = '';
  registerErrorMessage = '';
  roles: string[] = [];
  registerForm: any = {};
  isSuccessfulRegistered = false;
  isFailedRegistered = false;

  constructor(private authService: AuthService,
    private tokenStorage: TokenStorageService, public _userService: UserService) { }

  openLoginModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-login"]').tab('show');
  }

  openRegisterModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-register"]').tab('show');
  }

  logout() {
    this.authService.logout();
  }

  login() {
    this.authService.login(this.loginForm).subscribe(
      data => {
        $('#LoginModal').modal('hide');
        this.tokenStorage.saveToken(data.accessToken);
        console.log('userantes')
        this._userService.getUser();
        this.tokenStorage.saveUser(data);
        this._userService.user = data;
        this.roles = this.tokenStorage.getUser().roles;
      },
      err => {
        console.log('rere', err);
        this.loginErrorMessage = "El usuario no existe";
      }
    );
  }

  register() {
    this.authService.register(this.registerForm).subscribe(
      data => {
        this.isSuccessfulRegistered = true;
        this.isFailedRegistered = false;
      },
      err => {
        this.registerErrorMessage = err.error.message;
        this.isFailedRegistered = true;
      }
    );
  }

  ngOnInit(): void {
  }

}
