import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { PKIComponent } from './pki/pki.component';
import { PkiService } from './services/pki.service';

@NgModule({
  declarations: [
    AppComponent,
    PKIComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [PkiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
