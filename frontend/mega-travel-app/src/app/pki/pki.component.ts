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
      }, error => alert("Error: " + error)
    );

  }

  certificateSubmitted(response){
    alert("Uspsno ste dodali sertifikat");
  }

  onCloseForm(){
    this.genSelfSign = false;
  }
}
