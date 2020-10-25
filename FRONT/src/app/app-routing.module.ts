import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';


// Pages
import {
  CalculadoraDeCostesPage,
  CalculadoraDePreciosPage,
  ContactPage
} from "@pages/index.pages";
import { ProfilePage } from './pages/profile/profile.page.ts/profile.page';
import { RegisterPage } from './pages/register/register.page';


// Routes configuration
const appRoutes: Routes = [
  { path: 'c', component: CalculadoraDeCostesPage },
  { path: 'calculadoradeprecios', component: CalculadoraDePreciosPage },
  { path: 'register', component: RegisterPage },
  { path: 'profile', component: ProfilePage },
  { path: 'contacto', component: ContactPage },
  { path: '**', redirectTo: 'c', pathMatch: 'full' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: true, preloadingStrategy: PreloadAllModules })
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {
}
