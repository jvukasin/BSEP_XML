import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PkiService } from 'src/app/services/pki.service';

@Component({
  selector: 'app-add-certificate-form',
  templateUrl: './add-certificate-form.component.html',
  styleUrls: ['./add-certificate-form.component.css']
})
export class AddCertificateFormComponent implements OnInit {

  @Output() certificateAdd = new EventEmitter();
  @Input() softId;

  startDate: string;
  endDate: string;
  selfSigned: any;
  
  constructor(private pkiService: PkiService) { }

  ngOnInit() {
  }

  onSubmitCert(form: NgForm){
    let subjectDataDTO = {
      startDate: new Date(this.startDate),
      endDate: new Date(this.endDate),
    }

    this.pkiService.generateCertificate(this.softId, subjectDataDTO).subscribe(
      (response) => {
        this.certificateAdd.emit(response);
      },
      (error) => {alert("ERROR! something went wrong.")}
    )
    form.reset();
  }

}
