import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PKIComponent } from './pki/pki.component';


const appRoutes: Routes = [
	{ path: '', redirectTo: '/certificates', pathMatch: 'full'},
	{ path: 'certificates', component: PKIComponent }
]

@NgModule({
	imports: [RouterModule.forRoot(appRoutes)],
	exports: [ RouterModule ]
})

export class AppRoutingModule { }