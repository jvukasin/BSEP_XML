import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccommodationPageComponent } from './accommodation-page/accommodation-page.component';
import { AccommodationListComponent } from './accommodation-list/accommodation-list.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full'},
    { path: 'home', component: HomepageComponent },
    { path: 'login', component: LoginComponent},
	{ path: 'signup', component: RegisterComponent},
	{ path: 'accommodationsKrozID', component: AccommodationPageComponent},
	{ path: 'accommodations', component: AccommodationListComponent}
	]


@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }