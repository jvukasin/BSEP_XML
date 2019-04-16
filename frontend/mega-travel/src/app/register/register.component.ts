import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';

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
  city: FormControl;

  constructor(private http: HttpClient, private router: Router) { }

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
      password: this.password,
      city: this.city,
    });
  }

  onSubmitSignUp(form: NgForm){
    let user = {
      firstname: this.myForm.value.name,
      lastname: this.myForm.value.lastname,
      username: this.myForm.value.username,
      password: this.myForm.value.password,
      email: this.myForm.value.email,
      city: this.myForm.value.city
    }
    // this.service.saveUser(user).subscribe(
    //   (success) => {
    //     //dodati: nakon uspesne registracije prebaciti korisnika na glavnu stranicu 
    //      swal("Success!","Your registration was successfull. Check your e-mail to confirm your account.", "success");
    //      this.router.navigate(['/home']);
    //   },
    //   error => console.log(error)
    // );
    form.reset();
  }

}
