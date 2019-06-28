import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { CategoryService } from 'src/app/service/categories.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories: any;
  categoryForm : FormGroup;

  submitted: boolean = false;
  success: boolean = false;

  constructor(private formBuilder : FormBuilder, private categoryServie : CategoryService)
  {
      this.categoryForm = this.formBuilder.group({
        value: ['', [Validators.required, Validators.pattern("^[0-9]*$")] ]
      });
  }

  ngOnInit() 
  {
    this.categoryServie.getAllCategories().subscribe(
      (data) =>
      {
        this.categories = data;
        console.log(data);
      }
    )
  }

  onSubmit()
  {
      this.submitted = true;
      
      if(this.categoryForm.invalid)
      {
        console.log("Neuspesno.");
        return;
      }
      this.success = true;
      var cat = {value: this.categoryForm.controls.value.value}
      this.addToTable(cat);
  }


  addToTable(category)
  {
    this.categoryServie.addCategory(category).subscribe(
      (response) =>
      {
          this.categories.push(category);
          Swal.fire(
            "Added!",
            "Accommodation unit category has been successfuly added.",
            "success"
          )
      });
  }

  onRemove(category)
  {
    this.categoryServie.removeCategory(category).subscribe(
      (response) =>
      {
        this.categories.forEach((t,i) => {
          if(t.value == category)
          {
            this.categories.splice(i,1);
          }
        });
        Swal.fire(
          "Removed!",
          "Accommodation unit category has been successfuly removed.",
          "success"
        )
      });
  }


}
