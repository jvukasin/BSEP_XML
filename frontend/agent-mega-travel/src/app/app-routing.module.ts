import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { NewAccommodationComponent } from './accommodation/new-accommodation/new-accommodation.component';
import { ReservationComponent } from './reservation/reservation.component';
import { AccommodationListComponent } from './accommodation/accommodation-list/accommodation-list.component';
import { AccommodationUnitComponent } from './accommodation/accommodation-unit/accommodation-unit.component';
import { LoggedInGuard } from './_helper/logged-in.guard';
import { LoggedOutGuard } from './_helper/logged-out.guard';
import { ReservationListComponent } from './reservation/reservation-list/reservation-list.component';
import { MessagesComponent } from './reservation/messages/messages.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent, canActivate: [LoggedOutGuard]},
  { path: 'reset-password', component: ResetPasswordComponent, canActivate: [LoggedOutGuard]},
  { path: 'accommodation', component: AccommodationComponent, canActivate: [LoggedInGuard],
    canActivateChild: [LoggedInGuard],
    children: [
      { path: '', component: AccommodationListComponent},
      { path: 'new', component: NewAccommodationComponent},
      { path: ':id', component: AccommodationUnitComponent}
  ]},
  { path: 'reservation', component: ReservationComponent, canActivate: [LoggedInGuard],
   canActivateChild: [LoggedInGuard],
   children: [
    { path: '', component: ReservationListComponent},
    { path: ':id', component: MessagesComponent}
   ]}
	]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }