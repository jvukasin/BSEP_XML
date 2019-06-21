import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { AccommodationListComponent } from './accommodation/accommodation-list/accommodation-list.component';
import { AccommodationCardComponent } from './accommodation/accommodation-card/accommodation-card.component';
import { LoginComponent } from './login/login.component';
import { ReservationComponent } from './reservation/reservation.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewAccommodationComponent } from './accommodation/new-accommodation/new-accommodation.component';
import { AccommodationService } from './services/accommodation.service';
import { LocationService } from './services/location.service';
import { AccommodationUnitComponent } from './accommodation/accommodation-unit/accommodation-unit.component';
import { LoadingDirective } from './directives/loading.directive';
import { JwtInterceptor } from './_helper/jwt.interceptor';


@NgModule({
  declarations: [
    AppComponent,
  AccommodationComponent,
  AccommodationListComponent,
  AccommodationCardComponent,
  LoginComponent,
  ReservationComponent,
  NavbarComponent,
  NewAccommodationComponent,
  AccommodationUnitComponent,
  LoadingDirective
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  
  providers: [ AccommodationService, LocationService,
              {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true} ],

  bootstrap: [AppComponent]
})
export class AppModule { }