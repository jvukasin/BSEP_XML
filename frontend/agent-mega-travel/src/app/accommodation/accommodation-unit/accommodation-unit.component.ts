import { Component, OnInit } from '@angular/core';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { Router, Route, ActivatedRoute, Params } from '@angular/router';
import * as moment from 'moment';

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

	constructor(private acService: AccommodationService, private route: ActivatedRoute) {
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

	

}
