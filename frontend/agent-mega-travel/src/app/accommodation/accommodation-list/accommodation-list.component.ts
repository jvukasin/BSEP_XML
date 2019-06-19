import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccommodationService } from 'src/app/services/accommodation.service';

@Component({
  selector: 'app-accommodation-list',
  templateUrl: './accommodation-list.component.html',
  styleUrls: ['./accommodation-list.component.css']
})
export class AccommodationListComponent implements OnInit {

	accommodationUnits: any = null;

	constructor(private router: Router, private acService: AccommodationService) { 

		this.acService.getAllAccommodationUnits().subscribe(
			payload => this.accommodationUnits = payload,
			error => alert("Can't fetch accommodation units.")
		)
	}

	ngOnInit() {
	}

	onAddAccommodationUnit() {
		this.router.navigate(['accommodation/new']);
	}

}
