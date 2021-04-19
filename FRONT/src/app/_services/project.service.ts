import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../interfaces/project';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  step;
  originalProject: Project;
  project: Project = {
    type: '',
    name: '',
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

  constructor(private http: HttpClient) {
  }

  saveProject(project: Project) {
    return this.http.post(environment.baseUrl + 'user/save', project).subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

}
