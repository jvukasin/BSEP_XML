import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { PKIComponent } from './pki/pki.component';
import { PkiService } from './services/pki.service';
import { CertificateFormComponent } from './pki/certificate-form/certificate-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddCertificateFormComponent } from './pki/add-certificate-form/add-certificate-form.component';
import { RevokeCertificateFormComponent } from './pki/revoke-certificate-form/revoke-certificate-form.component';
import { SelfSignedComponent } from './self-signed/self-signed.component';
import { HeaderComponent } from './header/header.component';
import { TrustedSoftwareComponent } from './pki/trusted-software/trusted-software.component';

@NgModule({
  declarations: [
    AppComponent,
    PKIComponent,
    CertificateFormComponent,
    AddCertificateFormComponent,
    RevokeCertificateFormComponent,
    SelfSignedComponent,
    HeaderComponent,
    TrustedSoftwareComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PkiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
