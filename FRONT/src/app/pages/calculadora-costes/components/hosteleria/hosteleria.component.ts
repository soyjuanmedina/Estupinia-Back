import { Component, OnInit } from '@angular/core';
import { AccountingNote } from '../../../../interfaces/accountingNote';
import { Project } from '../../../../interfaces/project';
import { ProjectService } from '../../../../_services/project.service';
import { UserService } from '../../../../_services/user.service';
import { UtilitiesService } from '../../../../_services/utilities.service';
import { HttpClient } from '@angular/common/http';
import examples_old from "../../../../examples_old/hostelry.json";
declare var $: any;

@Component({
  selector: 'hosteleria-project',
  templateUrl: './hosteleria.component.html'
})
export class HosteleriaProject implements OnInit {

  examples: Array<Project>;
  emptyProject: Project = {
    type: 'Hostelería',
    name: '',
    esteemedCustomers: {
      esteemed: 0,
      commentary: '',
      averageTicket: 0,
      monthly: false
    },
    incomes: [],
    costs: {
      fixedcosts: [
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
      variablescosts: [
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
  }
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
  constructor(public _projectService: ProjectService, public _userService: UserService,
    private http: HttpClient, public _utilitiesService: UtilitiesService) {
    if (!this._projectService.project.id) {
      this._projectService.step = -1;
      this._projectService.project = this.emptyProject;
    }
    this.getExamples();
  }


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
      this._projectService.project.incomes = array.filter(function (el) { return el !== accountingNote; });
    } else if (type == 'fixedCost') {
      this._projectService.project.costs.fixedcosts = array.filter(function (el) { return el !== accountingNote; });
    } else if (type == 'variableCost') {
      this._projectService.project.costs.variablescosts = array.filter(function (el) { return el !== accountingNote; });
    }
  }

  addIncome() {
    let newIncome: AccountingNote = {
      name: this.incomeName,
      amount: this.incomeAmount
    }
    this._projectService.project.incomes.unshift(newIncome);
  }

  deleteIncome(incomes, income) {
    this._projectService.project.incomes = incomes.filter(function (el) { return el !== income; });
  }

  addFixedCost() {
    let newFixedCost: AccountingNote = {
      name: this.fixedCostName,
      amount: this.fixedCostAmount
    }
    this._projectService.project.incomes.unshift(newFixedCost);
  }

  deleteFixedCost(fixedCosts, fixedCost) {
    this._projectService.project.costs.fixedcosts = fixedCosts.filter(function (el) { return el !== fixedCost; });
  }

  addVariableExpend() {
    let newVariableExpend: AccountingNote = {
      name: this.variableExpendName,
      amount: this.variableExpendAmount
    }
    this._projectService.project.incomes.unshift(newVariableExpend);
  }

  deleteVariableExpend(variableExpends, variableExpend) {
    this._projectService.project.costs.variablescosts = variableExpends.filter(function (el) { return el !== variableExpend; });
  }

  next() {
    this._projectService.step = this._projectService.step + 1;
    location.href = '#section' + this._projectService.step;
  }

  calculate() {
    this.clearResults();
    this.calculated = true;
    location.href = '#result'
    this.totalRevenue = this._projectService.project.esteemedCustomers.esteemed * this._projectService.project.esteemedCustomers.averageTicket;
    if (!this._projectService.project.esteemedCustomers.monthly) {
      this.totalRevenue = this.totalRevenue * 30;
    }
    for (const income in this._projectService.project.incomes) {
      let amount = this._projectService.project.incomes[income].amount;
      this.totalRevenue = this.totalRevenue + amount
    }
    for (const fixedCost in this._projectService.project.costs.fixedcosts) {
      let amount = this._projectService.project.costs.fixedcosts[fixedCost].amount;
      this.totalFixedCosts = this.totalFixedCosts + amount
    }
    for (const variableExpend in this._projectService.project.costs.variablescosts) {
      let amount = this._projectService.project.costs.variablescosts[variableExpend].amount;
      this.totalVariableExpends = this.totalVariableExpends + amount
    }
    this.result = this.totalRevenue - this.totalFixedCosts - this.totalVariableExpends;
    if (isNaN(this.result)) {
      this.result = 0;
    }
  }

  clearResults() {
    this.result = 0;
    this.totalRevenue = 0;
    this.totalFixedCosts = 0;
    this.totalVariableExpends = 0;
  }

  reset() {
    location.href = '#section0';
    this._projectService.step = 0;
    this._projectService.project = this.emptyProject;
    this.clearResults();
    this.calculated = false;
  }

  loadExample(example) {
    delete example.id;
    example.type = "Hostelería"
    this._projectService.step = 3;
    this._projectService.project = example;
    console.log(this._projectService.project);
    setTimeout(() => {
      location.href = '#nombre'
    }, 500);
  }

  saveProjectToUser() {
    console.log("saveProjectToUser")
    if (this._projectService.project.type == "HosteleríaExample") {
      this._projectService.project.type = "Hostelería"
      delete this._projectService.project.costs.id
    }
    this._userService.saveProjectToUser(this._projectService.project);
  }

  getExamples() {
    this.examples = examples_old;
    /*     return this.http.post('/project/get/hostelery/examples', "").subscribe(
          data => {
            this.examples = data as Array<Project>;
          },
          err => {
            this._userService.error = err.error.message;
          }
        ); */
  }


  ngOnInit(): void {
  }

}
