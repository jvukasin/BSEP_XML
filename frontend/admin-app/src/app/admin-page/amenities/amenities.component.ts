import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AmenityService } from 'src/app/service/amenity.service';


@Component({
  selector: 'app-amenities',
  templateUrl: './amenities.component.html',
  styleUrls: ['./amenities.component.css']
})
export class AmenitiesComponent implements OnInit {

  amenities: any;
  amenityForm: FormGroup;

  submitted: boolean = false;
  success: boolean = false;

  constructor(private formBuilder : FormBuilder, private amenityService : AmenityService) 
  {
    this.amenityForm = this.formBuilder.group({
      name: ['', Validators.required],
      icon: ['', Validators.required]
    })
  }

  ngOnInit() 
  {
    this.amenityService.getAllAmenities().subscribe(
      (data) =>
      {
        this.amenities = data;
        console.log(data);
      }
    )
  }

  onSubmit()
  {
      this.submitted = true;
      
      if(this.amenityForm.invalid)
      {
        console.log("Neuspesno.");
        return;
      }
      this.success = true;
      var amenity = {name: this.amenityForm.controls.name.value, faIcon: this.amenityForm.controls.icon.value}
      this.addToTable(amenity);
  }

  addToTable(amenity)
  {
    this.amenityService.addAmenity(amenity).subscribe(
      (response) =>
      {
          console.log(response)
          
          var newAmenity = {id : response, name: amenity.name, faIcon: amenity.faIcon}

          this.amenities.push(newAmenity);

          alert("Added!");
      });
  }


  onRemove(amenityId)
  {
    this.amenityService.removeAmenity(amenityId).subscribe(
      (response) =>
      {
        this.amenities.forEach((a,i) => {
          if(a.id === amenityId)
          {
            this.amenities.splice(i,1);
            alert("Type: " + a.name + " removed.");
          }
        });
      });
  }


}
