import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  errorInpBool: boolean = false;

  loginForm = new FormGroup ({
     username: new FormControl(),
     password: new FormControl(),
  });
  
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }
  
  onSubmit(){
    const usr = this.loginForm.value.username;

    let user = {
      username: usr,
      password: this.loginForm.value.password
    }

    if(usr.includes('<') || usr.includes(' ') || usr.includes('>') || usr.includes(';')) {
      this.errorInpBool = true;
    } else {
      this.errorInpBool = false;
      this.authService.login(user).subscribe(
        (response) => {
          this.router.navigate(['/']);
        },
        (error) => { alert("User not logged in.") }
      )
    }
  }

}
