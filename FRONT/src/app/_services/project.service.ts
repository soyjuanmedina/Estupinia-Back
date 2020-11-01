import { Injectable } from '@angular/core';
import { Project } from '../interfaces/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  step;
  project: Project = {
    type: '',
    name,
    esteemedCustomers: {
      esteemed: 0,
      commentary: '',
      averageTicket: 0,
      monthly: false
    },
    incomes: [],
    costs: {
      fixedcosts: [],
      variablescosts: []
    }
  };

  constructor() {
  }
}
