import { Component, OnInit } from '@angular/core';
import { PkiService } from '../services/pki.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pki',
  templateUrl: './pki.component.html',
  styleUrls: ['./pki.component.css']
})
export class PKIComponent implements OnInit {


  
  sertifikati: any;
  softveri: any;
  selfSigned: any = null;
  //boolean za modalni dijalog, kad ga nema(selfSigned sertifikata) odmah se otvori, kad ima nista
  shoFormDialog: boolean = false;
  addCertifDialog: boolean = false;
  revokeCertifDialog: boolean = false;
  trustedSWDialog: boolean = false;
  
  softId: any;
  softsert: any = [];
  software: any = null;

  constructor(private pkiService: PkiService, private router: Router) { }

  ngOnInit() {

   this.fetchSelfSigned();
   this.fetchSofwares();
   this.fetchCertificates();

  }

  fetchSofwares() {

    this.pkiService.getSoftwares().subscribe(
      (data) => {
        this.softveri = data;
        for(var s of this.softveri) {
          if(s.certified) {
            s.certificate.startDate = s.certificate.startDate.split('T')[0];
            s.certificate.endDate = s.certificate.endDate.split('T')[0];
            this.softsert.push(s);
          }
        }
      }, error => alert("Error: " + error)
    );
  }

  fetchCertificates() {
    
    this.pkiService.getCertificates().subscribe(
      (data) => {
        this.sertifikati = data;
      }, error => alert("Error: " + error)
    );
  }
  

  fetchSelfSigned() {
    this.pkiService.getSelfSignedCert().subscribe(
			response => {
        this.selfSigned = response;
        if (this.selfSigned == null) {
          this.router.navigate(['/']);
        }
			},
			error => console.log(error)
		)
  }

  certificateAdded(s) {
    alert("SUCCESS! Certificate added.");
    this.fetchSofwares();
    this.fetchCertificates();
    this.addCertifDialog = false;
  }

  certificateRevoked(s) {
    alert("SUCCESS! Certificate revoked.")  
    this.sertifikati = s;
    
    this.fetchSofwares();
    this.fetchSelfSigned();

    this.revokeCertifDialog = false;
  }


  onCloseForm(){
    this.addCertifDialog = false;
  }

  onCloseRevokeForm(){
    this.revokeCertifDialog = false;
  }

  onCreateCertificate(id) {
    this.addCertifDialog = true;
    this.softId = id;
  }

  onRevokeCertificate(id) {
    this.revokeCertifDialog = true;
    this.softId = id;
  }

  onOpenTrustedSWDialog(sw) {
    console.log(sw);
    this.software = sw;
    console.log(this.software);
    this.trustedSWDialog = true;
  }

  onCloseTrustedSWDialog() {
    this.trustedSWDialog = false;
  }

  onAddTrustedSW($event) {
    console.log($event);
  }

  verifyCertificate(id){
    this.pkiService.verifyCertificate(id).subscribe(
      response => {
      }, error => {
          if (error.status === 200) {
            alert(error.error.text);
          }
        }
      
    )
  }

}
