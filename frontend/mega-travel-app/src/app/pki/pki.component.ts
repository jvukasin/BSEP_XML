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
  genSelfSign: boolean = false;
  shoFormDialog: boolean = false;
  addCertifDialog: boolean = false;
  softId: any;
  softsert: any = [];

  constructor(private pkiService: PkiService) { }

  ngOnInit() {
    this.pkiService.getSelfSignedCert().subscribe(
      (data) => {
        this.selfSigned = data;
        if(!this.selfSigned) {
          //ako vrati false, otvori modalni i napravi selfsigned za admina
          this.genSelfSign = true;
        }
      }
    );

    this.pkiService.getSoftwares().subscribe(
      (data) => {
        this.softveri = data;
        for(var s of this.softveri) {
          if(s.certified) {
            this.softsert.push(s);
          }
        }
      }, error => alert("Error: " + error)
    );

  }

  certificateSubmitted(response){
    alert("SUCCESS! Self-assigned certificate added.");
    this.genSelfSign = false;
  }

  onCloseForm(){
    this.addCertifDialog = false;
  }

  certificateAdded(s) {
    alert("SUCCESS! Certificate added.");
    let i = this.softveri.findIndex(soft => soft.id === s.id);
    this.softveri.splice(i, 1, s);
    this.softsert.push(s);
    this.addCertifDialog = false;
  }

  onCreateCertificate(id) {
    this.addCertifDialog = true;
    this.softId = id;
  }
}
