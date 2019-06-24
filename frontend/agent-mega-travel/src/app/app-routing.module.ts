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

const appRoutes: Routes = [
	{ path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent, canActivate: [LoggedOutGuard]},
  { path: 'accommodation', component: AccommodationComponent, canActivate: [LoggedInGuard],
    canActivateChild: [LoggedInGuard],
    children: [
      { path: '', component: AccommodationListComponent},
      { path: 'new', component: NewAccommodationComponent},
      { path: ':id', component: AccommodationUnitComponent},
  ]},
  { path: 'reservation', canActivate: [LoggedInGuard], canActivateChild: [LoggedInGuard], component: ReservationComponent}
	]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }