import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { ProjectService } from '../../_services/project.service';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'calculadora-costes-page',
  templateUrl: './calculadora-costes.page.html',
  styleUrls: ['./calculadora-costes.page.scss']
})
export class CalculadoraDeCostesPage implements OnInit {

  projectTypes = ['Hostelería'];
  projectType;

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(public _userService: UserService,
    public _projectService: ProjectService) {
  }


  navigateToProjectType() {
    location.href = '#projectType';
  }

  loadProject(project) {
    this._projectService.step = 3;
    this._projectService.project = this._userService.user.projects.filter(function (el) { return el.id == project.id; })[0];
    setTimeout(() => {
      location.href = '#nombre'
    }, 500);
  }

  ngOnInit(): void {
  }

}
