import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

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

  constructor(private router: Router,private fb: FormBuilder, private authService: AuthService) { }

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
