import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorInpBool: boolean = false;
  errorNotFOund: boolean = false;

  constructor(private service: AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginUser(form: NgForm){
    const usr = form.value.username;

    let user = {
      username: form.value.username,
      password: form.value.password,
    }
    
    if(usr.includes('<') || usr.includes(' ') || usr.includes('>') || usr.includes(';')) {
      this.errorInpBool = true;
    } else {
      this.errorInpBool = false;
      this.service.login(user).subscribe(
        (success) => {
          this.router.navigate(['/home']);
        },
        (error) => {
          if(error.status === 500){
            Swal.fire({
              type: 'error',
              title: 'Whoops! Something went wrong.',
              showConfirmButton: false,
              timer: 1600
            });
          }else if(error.status === 401){
            Swal.fire({
              type: 'error',
              title: 'Wrong username or password!',
              showConfirmButton: false,
              timer: 1600
            });
          } else if (error.status === 404) {
            Swal.fire({
              type: 'info',
              title: 'User not found! Wrong username or password.',
              showConfirmButton: false,
              timer: 1600
            });
          }
        }
      );
    }
  }

}
