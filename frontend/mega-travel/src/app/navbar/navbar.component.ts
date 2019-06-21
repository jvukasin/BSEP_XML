import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
	
	isLoggedIn: boolean = false;

	constructor(private authService: AuthService) {
	}

	ngOnInit() {
		let sad = localStorage.getItem("currentUser");
		if(localStorage.getItem("currentUser") != null) {
			this.isLoggedIn = true;
		}
		
	}

	LogOut() {
		this.authService.logout();
	}

}
