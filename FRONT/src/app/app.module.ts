import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NavbarComponent } from './components/navbar/navbar.component';
import { CalculadoraDeCostesPage } from './pages/calculadora-costes/calculadora-costes.page';
import { ContactPage } from './pages/contact/contact.page';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HosteleriaBusiness } from './components/businessTypes/hosteleria/hosteleria.component';
import { AppService } from './app.service';
import { ProfilePage } from './pages/profile/profile.page.ts/profile.page';
import { authInterceptorProviders } from './_helpers/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CalculadoraDeCostesPage,
    ContactPage,
    HosteleriaBusiness,
    ProfilePage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [AppService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
