import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccommodationPageComponent } from './accommodation-page/accommodation-page.component';

const appRoutes: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full'},
    { path: 'home', component: HomepageComponent },
    { path: 'login', component: LoginComponent},
	{ path: 'register', component: RegisterComponent},
	{ path: 'accommodation', component: AccommodationPageComponent}
	]


@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }