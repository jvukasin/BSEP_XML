import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  myForm: FormGroup;
  firstName: FormControl;
  lastName: FormControl;
  userName: FormControl;
  password: FormControl;
  email: FormControl;
  errFirst: boolean = false;
  errLast: boolean = false;
  errUsr: boolean = false;
  errMail: boolean = false;
  errPass: boolean = false;
  errUsrTaken: boolean = false;

  constructor(private http: HttpClient, private userService: UserService,private router: Router) { }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls(){
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required)
    this.userName = new FormControl('', Validators.required);
    this.password = new FormControl('', Validators.required);
    this.email = new FormControl('', {validators: [Validators.required, Validators.email]});
  }

  createForm(){
    this.myForm = new FormGroup({
      name: this.firstName,
      lastname: this.lastName,
      username: this.userName,
      email: this.email,
      password: this.password
    });
  }

  onSubmitSignUp(form: NgForm) {
    let user = {
      firstname: this.myForm.value.name,
      lastname: this.myForm.value.lastname,
      username: this.myForm.value.username,
      password: this.myForm.value.password,
      email: this.myForm.value.email
    }
    if(this.checkNames(user.firstname, user.lastname) && this.checkUsername(user.username) && this.checkMail(user.email) && this.checkPass(user.password)) {
      this.userService.registerUser(user).subscribe(
        (success) => {
            Swal.fire({
            type: 'success',
            title: 'You registered successfully!',
            showConfirmButton: false,
            timer: 1600
          });
           this.router.navigate(['/login']);
        },
        error => {
          Swal.fire({
            type: 'error',
            title: 'Something went wrong with your registration',
            showConfirmButton: true
          });
        }
      );
    }
  }

  checkNames(first, last) : boolean {
    const patt = /^[a-zA-Z]+$/;
    if(!patt.test(first)) {
      this.errFirst = true;
      return false;
    }
    this.errFirst = false;
    if(!patt.test(last)) {
      this.errLast = true;
      return false;
    }
    this.errLast = false;
    return true;
  }

  checkUsername(text) : boolean {
    if(text.includes('<') || text.includes(' ') || text.includes('>') || text.includes(';')) {
      this.errUsr = true;
      return false;
    }
    this.errUsr = false;
    return true;
  }

  checkPass(text) : boolean {
    const mailPatter = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\S+$).{8,}$/;
    if(!mailPatter.test(text)) {
      this.errPass = true;
      return false;
    }
    this.errPass = false;
    return true;
  }

  checkMail(text) : boolean {
    const mailPatter = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/;
    if(!mailPatter.test(text)) {
      this.errMail = true;
      return false;
    }
    this.errMail = false;
    return true;
  }

}
