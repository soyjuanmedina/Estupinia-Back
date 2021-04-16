import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilitiesService } from '../../_services/utilities.service';
import { ResponseDataBase } from '../../interfaces/responsedatabase.interface';
import { AppConstants } from '../../appConstants';
declare var $: any;

@Component({
  selector: 'app-page',
  templateUrl: './contact.page.html',
  styleUrls: ['./contact.page.scss']
})
export class ContactPage implements OnInit {
  name: string;
  email: string;
  message: string;
  thanks: string = '';
  errorAlert: string = '';

  constructor(public http: HttpClient, public _utilitiesService: UtilitiesService) { }

  contactMe() {
    this._utilitiesService.loading = true;
    delete this.errorAlert;
    delete this.thanks;
    let params = {
      email: this.email,
      name: this.name,
      message: this.message
    };
    this._utilitiesService.sendMail(params).subscribe(data => {
      data as ResponseDataBase;
      if (data) {
        this.thanks = "Gracias por tu mensaje, tendremos en cuenta tus comentarios.";
      } else {
        this.errorAlert = "Se ha producido un error, por favor vuelve a intentarlo en otro momento.";
        delete this.thanks;
      }
      this._utilitiesService.loading = false;
    });
  }

  ngOnInit(): void {
  }

}
