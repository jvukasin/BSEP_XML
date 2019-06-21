import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, Validators, FormControl, FormArray, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AccommodationService } from '../services/accommodation.service';
import { SearchResultsService } from '../services/search-results.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import * as moment from 'moment';

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
  todDate: Date;
  tomDate: Date;
  acctypes: any;

  constructor(private http: HttpClient, private router: Router,private accServise: AccommodationService, private srcres: SearchResultsService, private fb: FormBuilder) {}

  ngOnInit() {
    this.todDate = new Date();
    this.tomDate = new Date(+new Date() + 86400000);
    let tod = moment(this.todDate).format('YYYY-MM-DD');
    let tom = moment(this.tomDate).format('YYYY-MM-DD');
    this.createFormControls(tod, tom);
    this.createForm();

    this.accServise.getAccTypes().subscribe(
      (data) => {
        this.acctypes = data;
      }, (error) => {
        Swal.fire({
          type: 'error',
          title: 'Could not fetch accommodation types',
          showConfirmButton: false,
          timer: 1500
        });
      }
    )
  }

  createFormControls(tod, tom) {
    this.destination = new FormControl('', Validators.required);
    this.startDate = new FormControl(tod);
    this.endDate = new FormControl(tom);
    this.guests = new FormControl(1, Validators.required);
    this.accommodationType = new FormControl('');
    this.category = new FormControl('');
    this.distance = new FormControl('');
    this.cancellationPeriod = new FormControl('');
    this.averageRating = new FormControl('');
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
    let rat;
    let can;
    let dis;
    if(this.searchForm.value.averageRating == "") {
      rat = -1;
    } else {
      rat = this.searchForm.value.averageRating
    }
    if(this.searchForm.value.cancellationPeriod == "") {
      can = -1;
    } else {
      can = this.searchForm.value.cancellationPeriod;
    }
    if(this.searchForm.value.distance == "") {
      dis = -1;
    } else {
      dis = this.searchForm.value.distance;
    }
    let src = {
      city: tempDest,
      fromDate: this.searchForm.value.startDate,
      endDate: this.searchForm.value.endDate,
      personCount: this.searchForm.value.guests,
      ratingAvg: rat,
      cancellationPeriod: can,
      amenities: this.getSelectedAmenities(this.searchForm.get('amenities').value),
      type: this.searchForm.value.accommodationType,
      distanceFromCity: dis
    }

    console.log(src);

    this.accServise.search(src).subscribe(
      (data) => {
        this.srcres.accommodations = data;
        this.router.navigate(['/accommodations']);
      },
      (error) => {
          Swal.fire({
          type: 'error',
          title: 'Something went wrong',
          showConfirmButton: false,
          timer: 1500
        });
      }
    );

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
        error => {
          Swal.fire({
          type: 'error',
          title: 'Cannot get all amenities.',
          showConfirmButton: false,
          timer: 1500
        });
      }
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
  
  onKeydown(e) {
    if(!((e.keyCode > 95 && e.keyCode < 106)
      || (e.keyCode > 47 && e.keyCode < 58) 
      || e.keyCode == 8)) {
        return false;
    }
  }

}
