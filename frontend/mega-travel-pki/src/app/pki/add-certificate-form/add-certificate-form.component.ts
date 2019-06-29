import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgForm, Validators, FormBuilder } from '@angular/forms';
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

  caCerts: any[] = null;
  

	icForm = this.fb.group({
    alias: ['', Validators.required],
    issuerId: ['', Validators.required],
		startDate: ['', Validators.required],
    endDate: ['', Validators.required],
		ca: [''],
		cn: ['', Validators.required],
		o: ['', Validators.required],
		ou: ['', Validators.required],
		c: ['', Validators.required],
		e: ['', Validators.required]

	});

  constructor(private pkiService: PkiService, private fb: FormBuilder) { }

  ngOnInit() {
    this.pkiService.getCACertificates().subscribe(
      (payload: []) => this.caCerts = payload,
      error => alert(error)
    )
  }

  onSubmitIC(){
   
    const dto = {
      alias: this.icForm.get('alias').value,
      ca: this.icForm.get('ca').value,
      issuerId: this.icForm.get('issuerId').value,
      startDate: this.icForm.get('startDate').value,
      endDate: this.icForm.get('endDate').value,
      subjectDataDTO: {
        commonName: this.icForm.get('cn').value,
        organisation: this.icForm.get('o').value,
        organisationUnit: this.icForm.get('ou').value,
        countryCode: this.icForm.get('c').value,
        email: this.icForm.get('e').value
      }
      
    }

    console.log(dto);

    this.pkiService.generateIssuedCertificate(this.softId, dto).subscribe(
      (response) => {
        this.certificateAdd.emit(response);
      },
      (error) => {alert("ERROR! something went wrong.")}
    )
  }

}
