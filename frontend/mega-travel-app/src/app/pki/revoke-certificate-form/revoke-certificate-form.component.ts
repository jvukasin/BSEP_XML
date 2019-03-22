import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { PkiService } from 'src/app/services/pki.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-revoke-certificate-form',
  templateUrl: './revoke-certificate-form.component.html',
  styleUrls: ['./revoke-certificate-form.component.css']
})
export class RevokeCertificateFormComponent implements OnInit {

  @Output() certificateRevoke = new EventEmitter();
  @Input() softId;

  reasonForRevocation: string = '';

  constructor(private pkiService: PkiService) { }

  ngOnInit() {
  }

  onSubmitCert(form: NgForm){
    let revocationDTO = {
      subjectId: this.softId,
      reasonForRevocation: this.reasonForRevocation,
    }

    this.pkiService.revokeCertificate(revocationDTO).subscribe(
      (response) => {
        this.certificateRevoke.emit(response);
      },
      (error) => {alert("ERROR! something went wrong.")}
    )
    form.reset();
  }

}
