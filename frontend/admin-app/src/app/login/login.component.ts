import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup ({
     username: new FormControl(),
     password: new FormControl(),
  });
  
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }
  
  onSubmit(){
    let user = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    }
    //this.authService.login(){
    //}
    this.loginForm.reset();
  }

}
