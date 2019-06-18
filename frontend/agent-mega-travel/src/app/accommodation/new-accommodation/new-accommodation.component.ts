import { Component, OnInit } from '@angular/core';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { FormBuilder, Validators, FormArray } from '@angular/forms';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-new-accommodation',
  templateUrl: './new-accommodation.component.html',
  styleUrls: ['./new-accommodation.component.css']
})
export class NewAccommodationComponent implements OnInit {

	amenities: any = [];
	accommodationTypes: any = [];

	countries: any = [];
	cities: any = [];
	

	newAccommodationForm = this.fb.group({
		type: ['', Validators.required],
		capacity: ['', Validators.required],
		name: ['', Validators.required],
		description: ['', Validators.required],
		coordinates: ['', Validators.required],
		city: ['', Validators.required],
		country: ['', Validators.required],
		amenities: this.fb.array([]),
		defaultPrice: ['', Validators.required],
		cancellationPeriod: ['', Validators.required],
		specificPlans: this.fb.array([]),
		image: ['', Validators.required],
		images: this.fb.array([])
		
	  });

	constructor(private acService: AccommodationService, private fb: FormBuilder, private locationService: LocationService) {

		
		locationService.getCountries().subscribe(
			payload => {
				this.countries = payload;
				console.log(this.countries);
			}, error => {
				alert("Can't fetch countries");
			}

		);
		
		acService.getAccommodationSettings().subscribe(
			(data:any) => {
				this.amenities = data.amenities;
				this.accommodationTypes = data.accommodationTypes;

				this.renderAmenities();
				

			}, error => {
				alert("Can't fetch settings.");
			}
		)

	
	}

	
	ngOnInit() {

		
	}

	onChangeCountry(e) {
		this.locationService.getCountryCities(e.target.value).subscribe(
			payload => this.cities = payload,
			error => alert("Cant fetch country's cities")
				
			
		);
	}
 
	renderAmenities() {

		this.amenities.map(a => {
			this.newAccommodationForm.addControl("amenity" + a.id, this.fb.control(false, Validators.required));
		});
	}

	onAddPlan() {
		(<FormArray>this.newAccommodationForm.get('specificPlans')).push(
			this.fb.group({
				startDate: ['', Validators.required],
				endDate: ['', Validators.required],
				price: ['', Validators.required]
			})
		);

	}

	onDeletePlan(index) {
		(<FormArray>this.newAccommodationForm.get('specificPlans')).removeAt(index);
	}

	onAddImage() {
		(<FormArray>this.newAccommodationForm.get('images')).push(
			this.fb.group({
				imageUrl: ['', Validators.required],
			})
		);

	}

	onDeleteImage(index) {
		(<FormArray>this.newAccommodationForm.get('images')).removeAt(index);
	}


	

	onSubmit() {
		console.log(this.newAccommodationForm);

	}

}
