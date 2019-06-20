import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {

  types: any[] = [];
  typeForm : FormGroup;
    
  submitted: boolean = false;
  success: boolean = false;

  constructor(private formBuilder : FormBuilder) 
  {
      this.typeForm = this.formBuilder.group({
        name: ['', Validators.required]
      })
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
      //send rest req

      //ZAMENITI
      var type = {id:this.types.length+1 ,name: this.typeForm.controls.name.value}
      this.addToTable(type);
  }

  addToTable(type)
  {
      this.types.push(type)
  }

  onRemove(typeId)
  {
    //rest poziv
    this.types.forEach((t,i) => {
      if(t.id === typeId)
      {
        this.types.splice(i,1);
      }
    })
  }

  ngOnInit() {
  }

}
