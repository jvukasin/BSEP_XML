import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { AccommodationListComponent } from './accommodation/accommodation-list/accommodation-list.component';
import { AccommodationPageComponent } from './accommodation/accommodation-page/accommodation-page.component';
import { ProfileComponent } from './profile/profile.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full'},
	{ path: 'home', component: HomepageComponent},
	{ path: 'profile', component: ProfileComponent },
	{ path: 'login', component: LoginComponent},
	{ path: 'signup', component: RegisterComponent},
	{ path: 'accommodations', component: AccommodationComponent, children: [
		{ path: '', component: AccommodationListComponent},
		{ path: ':id', component: AccommodationPageComponent}
	]},
	]


@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }