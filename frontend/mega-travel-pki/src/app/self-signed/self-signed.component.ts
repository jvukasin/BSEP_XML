import { Component, OnInit } from '@angular/core';
import { PkiService } from '../services/pki.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-self-signed',
  templateUrl: './self-signed.component.html',
  styleUrls: ['./self-signed.component.css']
})
export class SelfSignedComponent implements OnInit {

	ssCertificate: any = {};

	ssForm = this.fb.group({
		alias: ['', Validators.required],
		startDate: ['', Validators.required],
		endDate: ['', Validators.required],
		cn: ['', Validators.required],
		o: ['', Validators.required],
		ou: ['', Validators.required],
		c: ['', Validators.required],
		e: ['', Validators.required]

	});

	constructor(private pkiService: PkiService, private fb: FormBuilder, private router: Router) { }

	ngOnInit() {

		this.pkiService.getSelfSignedCert().subscribe(
			response => {
				this.ssCertificate = response;
				if (response != null) this.router.navigate(['/pki']);
			},
			error => console.log(error)
		)


	}

	onSubmitSS() {
		const dto = {
			alias: this.ssForm.get('alias').value,
			startDate: this.ssForm.get('startDate').value,
			endDate: this.ssForm.get('endDate').value,
			subjectDataDTO: {
				commonName: this.ssForm.get('cn').value,
				organisation: this.ssForm.get('o').value,
				organisationUnit: this.ssForm.get('ou').value,
				countryCode: this.ssForm.get('c').value,
				email: this.ssForm.get('e').value
			}
		}

		this.pkiService.generateSelfSigned(dto).subscribe(
			payload => {
				this.ssCertificate = payload;
				if (this.ssCertificate != null) this.router.navigate(['/pki']);
			},
			error => alert(error.error)
		)
	}

}
