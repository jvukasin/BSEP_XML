import { Component, OnInit } from '@angular/core';
import { AccommodationService } from 'src/app/services/accommodation.service';
import { Router, Route, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-accommodation-unit',
  templateUrl: './accommodation-unit.component.html',
  styleUrls: ['./accommodation-unit.component.css']
})
export class AccommodationUnitComponent implements OnInit {

	id: number;
	accommodationUnit: any = null;
	category: any = [];

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


		// this.acService.getAccommodationUnit(this.id).subscribe(
		// 	payload => this.accommodationUnit = payload,
		// 	error => alert("Can't get accommodation unit.")
		// )

		this.accommodationUnit = {
			id: 1,
			name: "Cool waterfront apartment",
			description: "Lorem ipsum sit dolor amet. Description another one. Bla bla bla.",
			price: 75,
			avgRating: 9.2,
			type: "hotel",
			capacity: 3,
			category: 5,
			location: {
				coordinates: "Avenute des Champs-Elyseess 21",
				distanceFromCity: 2.1,
				city: {
					name: "Paris",
					country: {
						name: "France"
					}
				}
			},
			cancellationPeriod: 5
		}

		this.category = Array(this.accommodationUnit.category).map(i => i); 
	}

	

}
