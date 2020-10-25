import { Component, OnInit } from '@angular/core';
import restaurante from "./examples/restaurant.json";
import { Investment } from '../../interfaces/investment';
import { Cost } from '../../interfaces/cost';

@Component({
  selector: 'calculadora-costes-page',
  templateUrl: './calculadora-costes.page.html',
  styleUrls: ['./calculadora-costes.page.scss']
})
export class CalculadoraDeCostesPage implements OnInit {

  investments: Array<Investment> = [];
  costsResult: number = 0;
  investmentName: string;
  selectedInvestment: Investment;
  costName;
  costAmount;

  constructor() {
    // this.investments = restaurante.investments;
  }

  calculateCosts() {
    for (const investment in this.investments) {
      for (const cost in this.investments[investment].costs) {
        let amount = this.investments[investment].costs[cost].amount;
        this.costsResult = this.costsResult + amount;
      }
    }
  }

  selectInvestment(investment) {
    this.selectedInvestment = investment;
  }

  addInvestment() {
    let newInvestment: Investment = {
      type: this.investmentName,
      costs: []
    }
    this.investments.unshift(newInvestment);
  }

  addCost() {
    let newCost: Cost = {
      name: this.costName,
      amount: this.costAmount
    }
    this.selectedInvestment.costs.unshift(newCost);
  }

  deleteInvestment(investment) {
    console.log(investment);
    this.investments = this.investments.filter(function (el) { return el !== investment; });
  }

  deleteCost(investment, cost) {
    investment.costs = investment.costs.filter(function (el) { return el !== cost; });
  }

  calcAgain() {
    this.costsResult = 0;
  }

  loadExample(example) {
    console.log(example);
    this.investments = restaurante.investments;
  }

  clearInvestments() {
    this.investments = [];
  }

  ngOnInit(): void {
  }

}
