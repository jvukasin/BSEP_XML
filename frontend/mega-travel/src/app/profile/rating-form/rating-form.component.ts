import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AccommodationService } from 'src/app/services/accommodation.service';
import * as moment from 'moment';
@Component({
  selector: 'app-rating-form',
  templateUrl: './rating-form.component.html',
  styleUrls: ['./rating-form.component.css']
})
export class RatingFormComponent implements OnInit {
  
  @Output() ratingSubmit = new EventEmitter();
  @Input() accommodationId: any;

  ratingForm = new FormGroup ({
    rating: new FormControl(),
    comment: new FormControl(),
 });

  constructor(private accommodationService: AccommodationService) { }

  ngOnInit() {
  }

  onSubmit(){
    let rating = {
      value: this.ratingForm.value.rating,
      approved: 0,
      posting_date: null,
      comment: this.ratingForm.value.comment,
      accommodation_id: this.accommodationId,
    }

    this.accommodationService.postRating(rating).subscribe(
      (response) => {
        this.ratingSubmit.emit(response);
      }
    )
  }




}
