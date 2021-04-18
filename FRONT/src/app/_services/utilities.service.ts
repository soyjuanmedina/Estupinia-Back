import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
declare var $: any;
import { environment } from '../../environments/environment';

const UTILITIES_CONTROLLER = environment.baseUrl + 'utilities/';

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {

  loading: boolean;

  constructor(private http: HttpClient) { }

  openLoginModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-login"]').tab('show');
  }

  openRegisterModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-register"]').tab('show');
  }

  sendMail(params) {
    let url = UTILITIES_CONTROLLER + 'sendMail';
    return this.http.post(url, params)
  }
}
