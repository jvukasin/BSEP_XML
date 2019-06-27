import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { UsersComponent } from './admin-page/users/users.component';
import { CommentsComponent } from './admin-page/comments/comments.component';
import { LoginComponent } from './login/login.component';
import { AmenitiesComponent } from './admin-page/amenities/amenities.component';
import { TypesComponent } from './admin-page/types/types.component';
import { AgentComponent } from './admin-page/agent/agent.component';
import { CategoriesComponent } from './admin-page/categories/categories.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { AuthService } from './service/auth.service';
import { UserService } from './service/user.service';
import { AmenityService } from './service/amenity.service';
import { AgentService } from './service/agent.service';
import { TypeService } from './service/type.service';
import { CategoryService } from './service/categories.service';
import { ApproveAgentComponent } from './admin-page/approve-agent/approve-agent.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminPageComponent,
    UsersComponent,
    CommentsComponent,
    LoginComponent,
    AmenitiesComponent,
    TypesComponent,
    AgentComponent,
    CategoriesComponent,
    AgentComponent,
    ApproveAgentComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ AuthService, UserService, AmenityService, AgentService, TypeService, CategoryService,
              {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true} ],
  bootstrap: [AppComponent]
})
export class AppModule { }
