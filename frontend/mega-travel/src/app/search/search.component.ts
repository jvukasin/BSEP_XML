import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
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

  constructor(private http: HttpClient, private router: Router,private accServise: AccommodationService, private srcres: SearchResultsService) {}

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.destination = new FormControl('', Validators.required);
    this.startDate = new FormControl('');
    this.endDate = new FormControl('');
    this.guests = new FormControl('', Validators.required);
  }

  createForm() {
    this.searchForm = new FormGroup({
      destination: this.destination,
      startDate: this.startDate,
      endDate: this.endDate,
      guests: this.guests
    });
  }

  onSubmitSearch(form: NgForm) {
    let tempDest = this.searchForm.value.destination.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(/"/g, '&#x27;');
    let src = {
      city: tempDest,
      fromDate: this.searchForm.value.startDate,
      endDate: this.searchForm.value.endDate,
      personCount: this.searchForm.value.guests,
      ratingAvg: 0,
      amenities: [],
      type: "",
      category: ""
    }

    this.accServise.search(src).subscribe(
      (data) => {
        this.srcres.accommodations = data;
        this.router.navigate(['/accommodations']);
      },
      (error) => alert('ERROR')
    );

    this.srcres.destination = tempDest;
    this.srcres.startDate = this.searchForm.value.startDate;
    this.srcres.endDate = this.searchForm.value.endDate;
    this.srcres.guests = this.searchForm.value.guests;

  }

}
