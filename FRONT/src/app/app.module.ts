import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NavbarComponent } from './components/navbar/navbar.component';
import { CalculadoraDeCostesPage } from './pages/calculadora-costes/calculadora-costes.page';
import { CalculadoraDePreciosPage } from './pages/calculadora-precios/calculadora-precios.page';
import { ContactPage } from './pages/contact/contact.page';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HosteleriaBusiness } from './components/businessTypes/hosteleria/hosteleria.component';
import { AppService } from './app.service';
import { RegisterPage } from './pages/register/register.page';
import { ProfilePage } from './pages/profile/profile.page.ts/profile.page';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CalculadoraDeCostesPage,
    CalculadoraDePreciosPage,
    ContactPage,
    HosteleriaBusiness,
    RegisterPage,
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
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
