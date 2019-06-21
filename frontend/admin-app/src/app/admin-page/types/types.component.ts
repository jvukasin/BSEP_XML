import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { TypeService } from 'src/app/service/type.service';

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {

  types: any;
  typeForm : FormGroup;
    
  submitted: boolean = false;
  success: boolean = false;

  constructor(private formBuilder : FormBuilder, private typeService : TypeService) 
  {
      this.typeForm = this.formBuilder.group({
        name: ['', Validators.required]
      })
  }

  ngOnInit()
  {
    this.typeService.getAllTypes().subscribe(
      (data) =>
      {
        this.types = data;
        console.log(data);
      }
    )
  }


    
  onSubmit()
  {
      this.submitted = true;
      
      if(this.typeForm.invalid)
      {
        console.log("Neuspesno.");
        return;
      }
      this.success = true;
      var type = {type: this.typeForm.controls.name.value}
      this.addToTable(type);
  }

  addToTable(type)
  {
    this.typeService.addType(type).subscribe(
      (response) =>
      {
          this.types.push(type);
          alert("Added!");
      });
  }

  onRemove(type)
  {
    this.typeService.removeType(type).subscribe(
      (response) =>
      {
        this.types.forEach((t,i) => {
          if(t.type === type)
          {
            this.types.splice(i,1);
            alert("Type: " + type + " removed.");
          }
        });
      });
  }
  
}
