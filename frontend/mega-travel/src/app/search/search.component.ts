import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, Validators, FormControl, FormArray, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AccommodationService } from '../services/accommodation.service';
import { SearchResultsService } from '../services/search-results.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchForm: FormGroup;
  destination: FormControl;
  startDate: FormControl;
  endDate: FormControl;
  guests: FormControl;
  accommodationType: FormControl;
  category: FormControl;
  distance: FormControl;
  cancellationPeriod: FormControl;
  averageRating: FormControl;

  showAdvancedSearch: boolean = false;
  showMoreBox: boolean = false;
  amenities: any = null;

  constructor(private http: HttpClient, private router: Router,private accServise: AccommodationService, private srcres: SearchResultsService, private fb: FormBuilder) {}

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.destination = new FormControl('', Validators.required);
    this.startDate = new FormControl('');
    this.endDate = new FormControl('');
    this.guests = new FormControl('', Validators.required);
    this.accommodationType = new FormControl('');
    this.category = new FormControl('');
    this.distance = new FormControl('');
    this.cancellationPeriod = new FormControl(''),
    this.averageRating = new FormControl('')
  }

  createForm() {
    this.searchForm = new FormGroup({
      destination: this.destination,
      startDate: this.startDate,
      endDate: this.endDate,
      guests: this.guests,
      accommodationType: this.accommodationType,
      category: this.category,
      distance: this.distance,
      amenities: new FormArray([]),
      cancellationPeriod: this.cancellationPeriod,
      averageRating: this.averageRating

    });
  }

  onSubmitSearch(form: NgForm) {
    let tempDest = this.searchForm.value.destination.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/"/g, '&#x27;');
    let src = {
      city: tempDest,
      fromDate: this.searchForm.value.startDate,
      endDate: this.searchForm.value.endDate,
      personCount: this.searchForm.value.guests,
      ratingAvg: this.searchForm.value.averageRating,
      cancellationPeriod: this.searchForm.value.cancellationPeriod,
      amenities: this.getSelectedAmenities(this.searchForm.get('amenities').value),
      type: this.searchForm.value.accommodationType,
      distanceFromCity: this.searchForm.value.distance
    }

    console.log(src);

    // this.accServise.search(src).subscribe(
    //   (data) => {
    //     this.srcres.accommodations = data;
    //     this.router.navigate(['/accommodations']);
    //   },
    //   (error) => alert('ERROR')
    // );

    this.srcres.destination = tempDest;
    this.srcres.startDate = this.searchForm.value.startDate;
    this.srcres.endDate = this.searchForm.value.endDate;
    this.srcres.guests = this.searchForm.value.guests;

  }

  onClickMore() {
    console.log('ping');
    this.showAdvancedSearch = true;

    if (this.amenities == null) {
      this.accServise.getAllAmenities().subscribe(
        payload =>  {
          this.amenities = payload;
          this.renderAmenities();
        },
        error => alert("Can't get all amenities.")
      )
    }

  }

  onClickLess() {
    this.showAdvancedSearch = false;
    this.showMoreBox = false;

    // mozda i resetovati fields
  }

  onClickExpandMore() {
    console.log('ping');
    this.showMoreBox = true;

  }

  onClickCloseMore() {
    this.showMoreBox = false;
  }

  private renderAmenities() {

		this.searchForm.setControl('amenities', this.mapToCheckboxArrayGroup(this.amenities));
		
	}

	private getSelectedAmenities(amenitiesFormArray) {
		return amenitiesFormArray.filter(a => a.selected);
	}
	
	private mapToCheckboxArrayGroup(amenities) {
		return new FormArray(amenities.map((a) => {
			return this.fb.group({
			id: a.id,
			name: a.name,
			faIcon: a.faIcon,
			selected: [false, Validators.required]
			});
		}));
	}

}
