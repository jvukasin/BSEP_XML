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

	errorInpBool: boolean = false;

	loginForm = this.fb.group({
		username: ['', Validators.required],
		password: ['', Validators.required]
	  });

	constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
		this.authService.fetchAgents().subscribe();

	}

	ngOnInit() {

	}

	onSubmit() {

		const usr = this.loginForm.get('username').value;

		let dto = {
			username: usr,
			password: this.loginForm.get('password').value
		}
		
		if(usr.includes('<') || usr.includes(' ') || usr.includes('>') || usr.includes(';')) {
			this.errorInpBool = true;
		  } else {
			this.errorInpBool = false;
			this.authService.login(dto).subscribe(
				payload => {
					console.log(payload),
					this.router.navigate(['/accommodation']);
	
				},
				error => alert('Bad credentials.')
			);
		  }

	}

}
