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

  constructor(private pkiService: PkiService) { }

  ngOnInit() {
    this.pkiService.getSoftwares().subscribe(
      (data) => {
        this.softveri = data;
      }, error => alert("Error: " + error)
    );

  }

}
