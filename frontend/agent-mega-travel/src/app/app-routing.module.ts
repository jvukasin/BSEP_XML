import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { NewAccommodationComponent } from './accommodation/new-accommodation/new-accommodation.component';
import { ReservationComponent } from './reservation/reservation.component';
import { AccommodationListComponent } from './accommodation/accommodation-list/accommodation-list.component';
import { AccommodationUnitComponent } from './accommodation/accommodation-unit/accommodation-unit.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/login', pathMatch: 'full'},
    { path: 'login', component: LoginComponent },
    { path: 'accommodation', component: AccommodationComponent, children: [
        { path: '', component: AccommodationListComponent},
        { path: 'new', component: NewAccommodationComponent},
        { path: ':id', component: AccommodationUnitComponent},
    ]},
    { path: 'reservation', component: ReservationComponent}
	]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }