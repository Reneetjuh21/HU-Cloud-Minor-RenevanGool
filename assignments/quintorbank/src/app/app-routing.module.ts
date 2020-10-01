import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';

import { LoginComponent } from './components/login/login.component';
import { UserdetailsComponent } from './components/userdetails/userdetails.component';
import { HomeComponent } from './components/home/home.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

import { AuthService } from './services/auth.service';
import { AuthGuard } from './auth.guard';

const appRoutes: Routes = [
  { path: 'aanmelden', component: LoginComponent },
  { path: 'home', component: HomeComponent},
  { path: 'user-details', component: UserdetailsComponent, canActivate: [AuthGuard] },
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes// <-- debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ],
  providers: [AuthService]
})

export class AppRoutingModule { }