import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	loginForm = this.fb.group({
		username: ['', Validators.required],
		password: ['', Validators.required]
	  });

	constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {


	}

	ngOnInit() {

	}

	onSubmit() {

		let dto = {
			username: this.loginForm.get('username').value,
			password: this.loginForm.get('password').value
		}
		
		this.authService.login(dto).subscribe(
			payload => {
				console.log(payload),
				this.router.navigate(['/accommodation']);

			},
			error => alert('Something went wrong.')
		);

	}

}
