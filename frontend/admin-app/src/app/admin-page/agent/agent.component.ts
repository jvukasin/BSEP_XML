import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AgentService } from 'src/app/service/agent.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {

  agentForm: FormGroup;

  submitted: boolean = false;
  success: boolean = false;

  usernameTaken: boolean = false;

  errFirst: boolean = false;
  errLast: boolean = false;
  errUsr: boolean = false;
  errMail: boolean = false;
  errPass: boolean = false;

  constructor(private formBuilder : FormBuilder, private agentService : AgentService) 
  {
      this.agentForm = this.formBuilder.group({
        firstname: ['', Validators.required],
        lastname: ['', Validators.required],

        username: ['', Validators.required],
        password: ['', Validators.required],

        email: ['', [Validators.required, Validators.email]],
        registrationNumber: ['', Validators.required]
      })
  }

  ngOnInit()
  {
  }

  onSubmit()
  {
      this.submitted = true;
      
      if(this.agentForm.invalid)
      {
        console.log("Neuspesno.");
        return;
      }
      this.success = true;
      var agent = 
      {
        firstname: this.agentForm.controls.firstname.value, 
        lastname: this.agentForm.controls.lastname.value, 
        username: this.agentForm.controls.username.value,
        password: this.agentForm.controls.password.value,
        email: this.agentForm.controls.email.value,
        registrationNumber: this.agentForm.controls.registrationNumber.value        
      }

      if(this.checkNames(agent.firstname, agent.lastname) && this.checkUsername(agent.username) && this.checkMail(agent.email) && this.checkPass(agent.password)) {
      this.send(agent);
      }
  }


  send(agent)
  {

    this.usernameTaken = false;

    this.agentService.checkIfUsernameIsValid(agent.username).subscribe(
      (resp) =>
      {
         if(resp.status == 200)
         {
          this.agentService.addAgent(agent).subscribe(
            (response) =>
            {
              if(response.status == 201)
              {
                Swal.fire(
                  "Added!",
                  "Agent has been successfully added.",
                  "success"
                )
                this.agentForm.reset();
              }
            });
         }
         else
         {
           this.usernameTaken = true;
         }
      });
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
