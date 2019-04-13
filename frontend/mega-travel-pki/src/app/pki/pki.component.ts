import { Component, OnInit } from '@angular/core';
import { PkiService } from '../services/pki.service';

@Component({
  selector: 'app-pki',
  templateUrl: './pki.component.html',
  styleUrls: ['./pki.component.css']
})
export class PKIComponent implements OnInit {

  sertifikati: any;
  softveri: any;
  selfSigned: any;
  //boolean za modalni dijalog, kad ga nema(selfSigned sertifikata) odmah se otvori, kad ima nista
  shoFormDialog: boolean = false;
  addCertifDialog: boolean = false;
  revokeCertifDialog: boolean = false;
  
  softId: any;
  softsert: any = [];

  constructor(private pkiService: PkiService) { }

  ngOnInit() {
    
    

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

  

  certificateAdded(s) {
    alert("SUCCESS! Certificate added.");
    s.certificate.startDate = s.certificate.startDate.split('T')[0];
    s.certificate.endDate = s.certificate.endDate.split('T')[0];
    let i = this.softveri.findIndex(soft => soft.id === s.id);
    this.softveri.splice(i, 1, s);
    this.softsert.push(s);
    this.addCertifDialog = false;
  }

  certificateRevoked(s) {
    alert("SUCCESS! Certificate revoked.")  
    this.softsert = this.softsert.map(softver => {

      if (softver.id === s.id) {
        softver.certificate.revoked = true;
        softver.certificate.reasonForRevocation = s.certificate.reasonForRevocation;
      }

      return softver;

    });

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

  verifyCertificate(id){
    this.pkiService.verifyCertificate(id).subscribe(
      (verified) => {
        if(verified){
          alert("Certificate is valid!");
        }else{
          alert("Certificate is not valid!");
        }
      }
    )
  }

}
