import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorInpBool: boolean = false;

  constructor(private service: AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginUser(form: NgForm){
    const usr = form.value.username;

    let user = {
      username: form.value.username,
      password: form.value.password,
    }
    
    if(usr.includes('\'') || usr.includes('<') || usr.includes(' ') || usr.includes('>') || usr.includes(';')) {
      this.errorInpBool = true;
    } else {
      this.errorInpBool = false;
      this.service.login(user).subscribe(
        (success) => {
          this.router.navigate(['/home']);
        },
        (error) => {
          if(error.status === 500){
            alert("WHOOPS. Something went wrong!");
          }else if(error.status === 401){
            alert("Wrong username or password!");
          } else if (error.status === 404) {
            alert("NOT FOUND! Wrong username or password!");
          }
        }
      );
    }
  }

}
