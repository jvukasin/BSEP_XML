import { Component, OnInit } from '@angular/core';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { FormBuilder, Validators, FormArray } from '@angular/forms';
import { LocationService } from 'src/app/services/location.service';
import { formDirectiveProvider } from '@angular/forms/src/directives/ng_form';
import { Router } from '@angular/router';

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
		name: ['', Validators.required],
		description: ['', Validators.required],
		type: ['', Validators.required],
		capacity: ['', Validators.required],
		cancellationPeriod: ['', Validators.required],
		defaultPrice: ['', Validators.required],
		coordinates: ['', Validators.required],
		distanceFromCity: ['', Validators.required],
		city: ['', Validators.required],
		country: ['', Validators.required],
		specificPlans: this.fb.array([]),
		image: ['', Validators.required],
		images: this.fb.array([]),
		amenities: this.fb.array
		
	  });

	constructor(private acService: AccommodationService, private fb: FormBuilder, private locationService: LocationService, private router: Router) {

		
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

		if (!this.validateForm()) return;

		this.acService.postAccommodationUnit(this.formDTO()).subscribe(
			id => this.router.navigate(['accommodation/' + id]),
			error => alert("Can't post new accommodation unit")
		);

		

	}


	private renderAmenities() {

		this.newAccommodationForm.setControl('amenities', this.mapToCheckboxArrayGroup(this.amenities));
		
	}

	private getSelectedAmenities(amenitiesFormArray) {
		return amenitiesFormArray.filter(a => a.selected);
	}
	
	private mapToCheckboxArrayGroup(amenities) {
		return this.fb.array(amenities.map((a) => {
			return this.fb.group({
			id: a.id,
			name: a.name,
			faIcon: a.faIcon,
			selected: [false, Validators.required]
			});
		}));
	}


	private validateForm() {
		return true;
	}

	private formDTO() {

		return {
			name: this.newAccommodationForm.get('name').value,
			description: this.newAccommodationForm.get('description').value,
			type: this.newAccommodationForm.get('type').value,
			capacity: this.newAccommodationForm.get('capacity').value,
			cancellationPeriod: this.newAccommodationForm.get('cancellationPeriod').value,
			defaultPrice: this.newAccommodationForm.get('defaultPrice').value,
			location: this.formLocationDTO( this.newAccommodationForm.get('coordinates').value,
											this.newAccommodationForm.get('distanceFromCity').value,
											this.newAccommodationForm.get('city').value),
			amenities: this.getSelectedAmenities(this.newAccommodationForm.get('amenities').value),
			specificPrices: this.newAccommodationForm.get('specificPlans').value,
			images: this.formImagesDTO(this.newAccommodationForm.get('image').value,
										this.newAccommodationForm.get('images').value)
		}

	}

	private formLocationDTO(coordinates, distanceFromCity, cityId) {

		return {
			coordinates: coordinates,
			distanceFromCity: distanceFromCity,
			city: this.cities[cityId]
		}
	}


	private formImagesDTO(imageUrl, images) {
		
		// put it inside of object (dummy)
		let image = {
			imageUrl: imageUrl
		}

		return [image, ...images];

	}

	

}
