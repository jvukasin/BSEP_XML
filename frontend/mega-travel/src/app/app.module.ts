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
import { AccommodationPageComponent } from './accommodation-page/accommodation-page.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    RegisterComponent,
    KeepHtmlPipe,
    NavbarComponent,
    FooterComponent,
    AccommodationPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    AngularFontAwesomeModule,
    ReactiveFormsModule
  ],
  
  providers: [AuthService, UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
