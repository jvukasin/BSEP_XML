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
  pretraga: string;
  srctekst: string = "";

  constructor(private service: AuthService, private router: Router) { }

  ngOnInit() {
    if(localStorage.length !== 0) {
      this.isLoggedIn = true;  
    }
  }

  logOut(){
    this.service.logout();
  }

  Pretrazi() {
    let pom = this.pretraga;
    pom = this.pretraga.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/"/g, '&#x27;');
    this.srctekst = pom;
  }

}
