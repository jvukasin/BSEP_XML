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
			payload => this.accommodationUnit = payload,
			error => alert("Can't get accommodation unit.")
		)

	}

}
