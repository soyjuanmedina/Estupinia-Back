import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../interfaces/project';
import { ProjectService } from '../../_services/project.service';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-shared',
  templateUrl: './shared.component.html',
  styleUrls: ['./shared.component.scss']
})
export class SharedComponent implements OnInit {

  response: Project;

  constructor(public route: ActivatedRoute, public http: HttpClient,
    public _projectService: ProjectService, public _userService: UserService,
    public router: Router) {
    this.getSharedProject();
  }

  getSharedProject() {
    let data;
    this.route.queryParams.subscribe(params => {
      data = params;
    });
    return this.http.post('project/getsharedproject', data).subscribe(
      data => {
        this.response = data as Project;
        if (this._userService.user && this._userService.user.projects.some(elem => elem.id === this.response.id)) {
        } else {
          this._projectService.project = this.response;
          this._projectService.project.isShared = true;
          this.router.navigate(['/c']);
        }
      },
      err => {
        this._userService.error = err.error.message;
      }
    );
  }

  ngOnInit(): void {
  }

}
