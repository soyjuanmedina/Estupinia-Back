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
  hosteleryExamples;
  otherExamples;

  constructor(private http: HttpClient) {
    console.log('Wake up',);
    this.getExamples("HosteleryExample").subscribe(data => {
      this.hosteleryExamples = data;
    });
    this.getExamples("OtherExample").subscribe(data => {
      this.otherExamples = data as Array<Project>;
    })
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

  getExamples(type) {
    let params = {
      type: type
    }
    let url = environment.baseUrl + 'project/get/examples';
    return this.http.post(url, params);
  }

}
