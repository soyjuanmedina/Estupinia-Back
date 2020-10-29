import { Component, OnInit } from '@angular/core';
import { AccountingNote } from '../../../interfaces/accountingNote';
import examples from "../../../examples/hostelry.json";
import { Project } from '../../../interfaces/project';
declare var $: any;

@Component({
  selector: 'business-hosteleria',
  templateUrl: './hosteleria.component.html',
  styleUrls: ['./hosteleria.component.scss']
})
export class HosteleriaBusiness implements OnInit {
  project: Project = {
    type: "Hostelería",
    name,
    esteemedCustomers: {
      esteemed: 0,
      commentary: '',
      averageTicket: 0,
      monthly: false
    },
    incomes: [],
    costs: {
      fixed: [
        {
          name: 'Alquiler',
          amount: 0
        },
        {
          name: 'Personal',
          amount: 0
        },
        {
          name: 'Gestoria',
          amount: 0
        }
      ],
      variables: [
        {
          name: 'Luz',
          amount: 0
        },
        {
          name: 'Agua',
          amount: 0
        },
        {
          name: 'Teléfono',
          amount: 0
        }
      ]
    }
  };
  selectedArray;
  accountingNoteName;
  accountingNoteAmount;
  accountingNoteCommentary;


  incomeName;
  incomeAmount;
  fixedCostName;
  fixedCostAmount;
  variableExpendName;
  variableExpendAmount;
  totalRevenue = 0;
  totalFixedCosts = 0;
  totalVariableExpends = 0;
  result = null;
  calculated = false;
  step = 0;
  constructor() { }


  addAccountingNote() {
    let newAccountingNote: AccountingNote = {
      name: this.accountingNoteName,
      amount: this.accountingNoteAmount,
      commentary: this.accountingNoteCommentary
    }
    this.selectedArray.unshift(newAccountingNote);
  }

  deleteAccountingNote(type, array, accountingNote) {
    if (type == 'incomes') {
      this.project.incomes = array.filter(function (el) { return el !== accountingNote; });
    } else if (type == 'fixedCost') {
      this.project.costs.fixed = array.filter(function (el) { return el !== accountingNote; });
    } else if (type == 'variableCost') {
      this.project.costs.variables = array.filter(function (el) { return el !== accountingNote; });
    }
  }

  addIncome() {
    let newIncome: AccountingNote = {
      name: this.incomeName,
      amount: this.incomeAmount
    }
    this.project.incomes.unshift(newIncome);
  }

  deleteIncome(incomes, income) {
    this.project.incomes = incomes.filter(function (el) { return el !== income; });
  }

  addFixedCost() {
    let newFixedCost: AccountingNote = {
      name: this.fixedCostName,
      amount: this.fixedCostAmount
    }
    this.project.incomes.unshift(newFixedCost);
  }

  deleteFixedCost(fixedCosts, fixedCost) {
    this.project.costs.fixed = fixedCosts.filter(function (el) { return el !== fixedCost; });
  }

  addVariableExpend() {
    let newVariableExpend: AccountingNote = {
      name: this.variableExpendName,
      amount: this.variableExpendAmount
    }
    this.project.incomes.unshift(newVariableExpend);
  }

  deleteVariableExpend(variableExpends, variableExpend) {
    this.project.costs.variables = variableExpends.filter(function (el) { return el !== variableExpend; });
  }

  next() {
    this.step = this.step + 1;
    location.href = '#section' + this.step;
  }

  calculate() {
    this.calculated = true;
    location.href = '#result'
    this.totalRevenue = this.project.esteemedCustomers.esteemed * this.project.esteemedCustomers.averageTicket;
    if (!this.project.esteemedCustomers.monthly) {
      this.totalRevenue = this.totalRevenue * 30;
    }
    for (const income in this.project.incomes) {
      let amount = this.project.incomes[income].amount;
      this.totalRevenue = this.totalRevenue + amount
    }
    for (const fixedCost in this.project.costs.fixed) {
      let amount = this.project.costs.fixed[fixedCost].amount;
      this.totalFixedCosts = this.totalFixedCosts + amount
    }
    for (const variableExpend in this.project.costs.variables) {
      let amount = this.project.costs.variables[variableExpend].amount;
      this.totalVariableExpends = this.totalVariableExpends + amount
    }
    this.result = this.totalRevenue - this.totalFixedCosts - this.totalVariableExpends;
    if (isNaN(this.result)) {
      this.result = 0;
    }
    console.log(this.calculated, typeof (this.result), this.result.isNaN, isNaN(this.result))
  }

  calcAgain() {
    location.href = '#section0';
    setTimeout(() => {                           //<<<---using ()=> syntax
      this.result = 0;
      this.step = 0;
      this.calculated = false;
    }, 500);
  }

  loadExample(example) {
    console.log(examples);
    this.step = 3;
    this.project = examples.filter(function (el) { return el.id == example; })[0];
    setTimeout(() => {
      location.href = '#nombre'
    }, 500);
  }


  ngOnInit(): void {
  }

}
