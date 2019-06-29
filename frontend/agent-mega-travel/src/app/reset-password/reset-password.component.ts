import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  successMsg: boolean = false;

  resetForm = this.fb.group({
		email: ['', Validators.required]
	  });

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
  }

  onSubmit() {

		let dto = {
			email: this.resetForm.get('email').value
			
		}
		
    console.log(dto);
    
    this.successMsg = true;

	}

}
