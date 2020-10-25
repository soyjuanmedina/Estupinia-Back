import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';

@Component({
  selector: 'calculadora-costes-page',
  templateUrl: './calculadora-costes.page.html',
  styleUrls: ['./calculadora-costes.page.scss']
})
export class CalculadoraDeCostesPage implements OnInit {

  businessTypes = ['Hostelería'];
  business;

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) {
  }


  navigateToBusiness() {
    location.href = '#business';
  }


  ngOnInit(): void {
  }

}
