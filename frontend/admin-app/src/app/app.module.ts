import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
