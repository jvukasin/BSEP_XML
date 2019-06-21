import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, NgForm } from '@angular/forms';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  isLoggedIn: boolean = false;
  profilePage: boolean = false;

  constructor(private service: AuthService, private router: Router) { }

  ngOnInit() {
    if(localStorage.length !== 0) {
      this.isLoggedIn = true;  
    }
  }

  logOut(){
    this.service.logout();
  }

}
