import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PkiService } from 'src/app/services/pki.service';

@Component({
  selector: 'app-certificate-form',
  templateUrl: './certificate-form.component.html',
  styleUrls: ['./certificate-form.component.css']
})
export class CertificateFormComponent implements OnInit {

  @Output() certificateSubmit = new EventEmitter();

  startDate: string;
  endDate: string;

  constructor(private pkiService: PkiService) { }

  ngOnInit() {
  }

  onSubmitCert(form: NgForm){
    let subjectDataDTO = {
      startDate: new Date(this.startDate),
      endDate: new Date(this.endDate),
    }

    this.pkiService.generateSelfSigned(subjectDataDTO).subscribe(
      (response) => {
        this.certificateSubmit.emit(response);
      },
      (error) => {alert("GRESKA")}
    )
    form.reset();
  }

}
