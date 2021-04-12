import { Component, OnInit } from '@angular/core';
import { AccountingNote } from '../../../../interfaces/accountingNote';
import { Project } from '../../../../interfaces/project';
import { ProjectService } from '../../../../_services/project.service';
import { UserService } from '../../../../_services/user.service';
import { UtilitiesService } from '../../../../_services/utilities.service';
import { HttpClient } from '@angular/common/http';
import examples_old from "../../../../examples_old/hostelry.json";
import { Router } from '@angular/router';
declare var $: any;

@Component({
	selector: 'hosteleria-project',
	templateUrl: './hosteleria.component.html',
	styleUrls: ['./hosteleria.component.scss']
})
export class HosteleriaProject implements OnInit {

	alert;
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
	window = window;
	constructor(public _projectService: ProjectService, public _userService: UserService,
		private http: HttpClient, public _utilitiesService: UtilitiesService, public router: Router) {
		console.log('hola');
		if (this._userService.user && !this._userService.user.projects.some(elem => elem.id === this._projectService.project.id &&
			elem.type === this._projectService.project.type)) {
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
		delete this.accountingNoteName;
		delete this.accountingNoteAmount;
		delete this.accountingNoteCommentary;
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
		location.href = '#startSection';
		this._projectService.step = -1;
		this._projectService.project = this.emptyProject;
		this.clearResults();
		this.calculated = false;
	}

	loadExample(example) {
		this._projectService.step = 3;
		this._projectService.project = { ...example };
		this.deleteProjectId();
		this._projectService.project.type = "Hostelería";
		setTimeout(() => {
			location.href = '#nombre'
		}, 500);
	}

	saveProjectToUser() {
		console.log('this._projectService.project')
		if (!this._projectService.project.id && this.projectNameExistsInUserProjects(this._projectService.project.name)) {
			this.alert = "Ya tienes un proyecto con este nombre"
			$('#SaveModal').modal('show');
		} else {
			location.href = '#nombre'
			if (this._projectService.project.isMine) {
				this._userService.user.projects = this._userService.user.projects.filter(obj => obj.id != this._projectService.project.id);
			}
			this._userService.user.projects.push(this._projectService.project);
			this._userService.saveUser(this._userService.user);
			this._projectService.project = this._userService.user.projects.filter(obj => obj.name === this._projectService.project.name)[0];
			this._projectService.project.isMine = true;
			setTimeout(() => {
				this.alert = "Proyecto guardado correctamente"
				$('#SaveModal').modal('show');
			}, 750);

		}
	}

	saveAsNewProjectToUser() {
		this.deleteProjectId();
		this.saveProjectToUser();
	}

	deleteProjectFromUser() {
		this._userService.user.projects = this._userService.user.projects.filter(obj => obj.id != this._projectService.project.id);
		this._userService.saveUser(this._userService.user);
		this.reset();
	}


	deleteProjectId() {
		delete this._projectService.project.id;
		if (this._projectService.project.costs) {
			delete this._projectService.project.costs.id;
			this._projectService.project.costs.fixedcosts.forEach(fixedcost => delete fixedcost.id);
			this._projectService.project.costs.variablescosts.forEach(variablecost => delete variablecost.id);
		}
		if (this._projectService.project.esteemedCustomers) {
			delete this._projectService.project.esteemedCustomers.id;
		}
		if (this._projectService.project.incomes) {
			this._projectService.project.incomes.forEach(income => delete income.id);
		}
	}

	getExamples() {
		// this.examples = examples_old;
		return this.http.post('/project/get/hostelery/examples', "").subscribe(
			data => {
				this.examples = data as Array<Project>;
			},
			err => {
				this._userService.error = err.error.message;
			}
		);
	}

	projectNameExistsInUserProjects(name) {
		return this._userService.user.projects.some(elem => elem.name === name);
	}

	shareProject() {
		$('#SaveModal').modal('show');
	}


	ngOnInit(): void {
	}

	ngOnDestroy(): void {
		$('.modal').modal('hide');
	}

}
