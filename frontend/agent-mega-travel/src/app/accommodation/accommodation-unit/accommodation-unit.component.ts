import { Component, OnInit } from '@angular/core';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { Router, Route, ActivatedRoute, Params } from '@angular/router';
import * as moment from 'moment';
import { FormBuilder, Validators } from '@angular/forms';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-accommodation-unit',
  templateUrl: './accommodation-unit.component.html',
  styleUrls: ['./accommodation-unit.component.css']
})
export class AccommodationUnitComponent implements OnInit {

	id: number;
	accommodationUnit: any = null;
	category: any = [];
	pricePlan: any = null;
	showReservationForm: boolean = false;
	today = moment().format("YYYY-MM-DD");
	tommorrow = moment().add(1, 'days').format("YYYY-MM-DD");
	
	showErrorLabel: boolean = false;

	reservationForm = this.fb.group({
		startDate: [this.today, Validators.required],
		endDate: [this.tommorrow, Validators.required]
		
	  });

	  endDateConstraint = moment(this.reservationForm.get('startDate').value).add(1, 'days').format("YYYY-MM-DD");

	constructor(private acService: AccommodationService, private route: ActivatedRoute, private router: Router,
				 private fb: FormBuilder, private reservationService: ReservationService) {
		// knowing whether to show navigatin or not
        // getting route params, params is observable that unsubscribes automatically
		this.route.params.subscribe(
			(params: Params) => {
				this.id = +params['id'];
			}
		);
	
	
	 }

	ngOnInit() {


		this.acService.getAccommodationUnit(this.id).subscribe(
			payload => {
				this.accommodationUnit = payload;
				if (this.accommodationUnit.category > 0) {
					console.log('ping');
					this.category = Array(this.accommodationUnit.category).map(i => i); 
				} 
			},
			error => alert("Can't get accommodation unit.")
		)


		
	}

	onSeeFullPricePlan() {
		if (!this.pricePlan) {
			this.acService.getPricePlan(this.id).subscribe(
				(payload) => { 
					this.pricePlan = payload;
					this.pricePlan.specificPrices.map(sp => {
					sp.startDate = moment(sp.startDate).format('LL');
					sp.endDate = moment(sp.endDate).format('LL');
					})
				},
				error => alert(error.message)
			);

		}
	}

	onChangeStartDate() {
		this.endDateConstraint = moment(this.reservationForm.get('startDate').value).add(1, 'days').format("YYYY-MM-DD");
		this.reservationForm.patchValue({'endDate': this.endDateConstraint});
		this.showErrorLabel = false;
	}

	onChangeEndDate() {
		this.showErrorLabel = false;
	}

	onClickReserve() {
		this.showReservationForm = true;
	}

	onSubmitReservation() {

		console.log(this.reservationForm);

		let dto = {
			startDate: this.reservationForm.get('startDate').value,
			endDate: this.reservationForm.get('endDate').value,
			accommodationUnitId: this.accommodationUnit.id

		}

		this.reservationService.postReservation(dto).subscribe(
			payload => {
				console.log(payload);
				this.router.navigate(['/reservation']);
				
			}, error => {
				if (error.status === 406) {
					this.showErrorLabel = true;
				}
			}
		);

		console.log(dto);

	}

	

}
