import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from "./helpers/jwt.interceptor"

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthService } from './services/auth.service';
import { RegisterComponent } from './register/register.component';
import { UserService } from './services/user.service';
import { KeepHtmlPipe } from './pipe/keep-html.pipe';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AccommodationPageComponent } from './accommodation/accommodation-page/accommodation-page.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { SearchComponent } from './search/search.component';
import { SliderComponent } from './homepage/slider/slider.component';
import { BestPlacesComponent } from './homepage/best-places/best-places.component';
import { BestFeaturedComponent } from './homepage/best-featured/best-featured.component';
import { AboutUsComponent } from './homepage/about-us/about-us.component';
import { AccommodationListComponent } from './accommodation/accommodation-list/accommodation-list.component';
import { AccommodationService } from './services/accommodation.service';
import { SearchResultsService } from './services/search-results.service';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { ProfileComponent } from './profile/profile.component';
import { ReservationService } from './services/reservation.service';
import { MessagesComponent } from './profile/messages/messages.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    RegisterComponent,
    KeepHtmlPipe,
    NavbarComponent,
    FooterComponent,
    AccommodationPageComponent,
    SearchComponent,
    SliderComponent,
    BestPlacesComponent,
    BestFeaturedComponent,
    AboutUsComponent,
    AccommodationListComponent,
    AccommodationComponent,
    ProfileComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    AngularFontAwesomeModule,
    ReactiveFormsModule
  ],
  
  providers: [AuthService, UserService, AccommodationService, SearchResultsService, ReservationService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
