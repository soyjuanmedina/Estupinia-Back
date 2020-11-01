import { Injectable } from '@angular/core';
declare var $: any;

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {

  constructor() { }

  openLoginModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-login"]').tab('show');
  }

  openRegisterModal() {
    $('#LoginModal').modal('show');
    $('.nav-tabs a[href="#nav-register"]').tab('show');
  }
}
