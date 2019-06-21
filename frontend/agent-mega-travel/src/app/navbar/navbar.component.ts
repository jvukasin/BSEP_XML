import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	isLoggedIn = false;
	userInfo = null;

	constructor(private authService: AuthService) { 
		if(this.isLoggedIn = this.authService.isLoggedIn()) {
			
			this.authService.getUser().subscribe(
				payload => this.userInfo = payload,
				error => console.error(error)
			);
		}


	}

	ngOnInit() {
	}

	onLogout() {
		this.authService.logout();
	}

}
