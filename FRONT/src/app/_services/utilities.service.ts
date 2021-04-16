import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
declare var $: any;

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
    let url = 'utilities/sendMail';
    return this.http.post(url, params)
  }
}
