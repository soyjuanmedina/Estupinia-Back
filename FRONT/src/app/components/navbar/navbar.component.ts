import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { TokenStorageService } from '../../_services/token-storage.service';

@Component({
  selector: 'navbar-component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  loginForm: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  loginErrorMessage = '';
  registerErrorMessage = '';
  roles: string[] = [];
  registerForm: any = {};
  isSuccessfulRegistered = false;
  isFailedRegistered = false;

  constructor(private authService: AuthService,
    private tokenStorage: TokenStorageService) { }

  onSubmitLoginForm(): void {
    this.authService.login(this.loginForm).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.loginErrorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  onSubmitRegisterForm(): void {
    this.authService.register(this.registerForm).subscribe(
      data => {
        console.log(data);
        this.isSuccessfulRegistered = true;
        this.isFailedRegistered = false;
      },
      err => {
        this.registerErrorMessage = err.error.message;
        this.isFailedRegistered = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

}
