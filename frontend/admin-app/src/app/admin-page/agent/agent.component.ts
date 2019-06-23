import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AgentService } from 'src/app/service/agent.service';


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
      this.send(agent);
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
                alert("Agent " + response + " created.");
            });
         }
         else
         {
           this.usernameTaken = true;
         }
         
         
      });
  }

}
