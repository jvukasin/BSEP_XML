import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { UsersComponent } from './admin-page/users/users.component';

const appRoutes: Routes = [
	{path: '', component: AdminPageComponent},
	{path: 'users', component: UsersComponent}
	]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})



export class AppRoutingModule { }