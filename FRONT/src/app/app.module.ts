import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// Interceptor http
import { AllHttpRequestsInterceptor } from "./interceptors/allhttprequests.interceptor";


import { NavbarComponent } from './components/navbar/navbar.component';
import { CalculadoraDeCostesPage } from './pages/calculadora-costes/calculadora-costes.page';
import { ContactPage } from './pages/contact/contact.page';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HosteleriaProject } from './pages/calculadora-costes/components/hosteleria/hosteleria.component';
import { AppService } from './app.service';
import { ProfilePage } from './pages/profile/profile.page.ts/profile.page';
import { SharedComponent } from '@pages/shared/shared.component';
import { OtherTypeComponent } from './pages/calculadora-costes/components/othertype/othertype.component';
import { AuthGuardService } from './_services/auth-guard/auth-guard.service';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CalculadoraDeCostesPage,
    ContactPage,
    HosteleriaProject,
    ProfilePage,
    SharedComponent,
    OtherTypeComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    AppService,
    AuthGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AllHttpRequestsInterceptor,
      multi: true
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }
