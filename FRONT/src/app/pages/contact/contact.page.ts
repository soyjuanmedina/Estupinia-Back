import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
  thanks: boolean = false;

  constructor(public http: HttpClient) { }

  contactMe() {
    console.log('this', this.email, this.name, this.message);
    let self = this;
    $.post('/assets/php/mail.php', {
      email: this.email,
      name: this.name,
      message: this.message
    }).then(function (value) {
      self.thanks = true; // Success!
    }, function (reason) {
      self.thanks = true; // Error!
    });
  }

  ngOnInit(): void {
  }

}
