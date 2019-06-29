import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PKIComponent } from './pki/pki.component';
import { SelfSignedComponent } from './self-signed/self-signed.component';


const appRoutes: Routes = [
	{ path: '', redirectTo: '/ss', pathMatch: 'full'},
	{ path: 'pki', component: PKIComponent },
	{ path: 'ss', component: SelfSignedComponent }
	
]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})

export class AppRoutingModule { }