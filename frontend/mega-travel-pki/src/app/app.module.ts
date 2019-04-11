import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { PKIComponent } from './pki/pki.component';
import { PkiService } from './services/pki.service';
import { CertificateFormComponent } from './pki/certificate-form/certificate-form.component';
import { FormsModule } from '@angular/forms';
import { AddCertificateFormComponent } from './pki/add-certificate-form/add-certificate-form.component';
import { RevokeCertificateFormComponent } from './pki/revoke-certificate-form/revoke-certificate-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PKIComponent,
    CertificateFormComponent,
    AddCertificateFormComponent,
    RevokeCertificateFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [PkiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
