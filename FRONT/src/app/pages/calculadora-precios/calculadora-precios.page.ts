import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'calculadora-precios-page',
  templateUrl: './calculadora-precios.page.html',
  styleUrls: ['./calculadora-precios.page.scss']
})
export class CalculadoraDePreciosPage implements OnInit {

  costs = {
    rawMaterial: null,
    handling: null
  };
  priceResult;

  constructor() { }

  calculatePrice() {
    this.priceResult = this.costs.rawMaterial + this.costs.handling;
  }

  calcAgain() {
    delete this.priceResult;
    this.costs = {
      rawMaterial: null,
      handling: null
    };
  }

  ngOnInit(): void {
  }

}
