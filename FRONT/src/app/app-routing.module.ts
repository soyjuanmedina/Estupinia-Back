import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';


// Pages
import {
  CalculadoraDeCostesPage,
  ContactPage
} from "@pages/index.pages";
import { SharedComponent } from '@pages/shared/shared.component';
import { ProfilePage } from './pages/profile/profile.page.ts/profile.page';


// Routes configuration
const appRoutes: Routes = [
  { path: 'c', component: CalculadoraDeCostesPage },
  { path: 'profile', component: ProfilePage },
  { path: 'contact', component: ContactPage },
  {
    path: 'shared',
    component: SharedComponent,
  },
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
