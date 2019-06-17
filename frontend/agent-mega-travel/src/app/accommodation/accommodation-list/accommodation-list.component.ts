import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accommodation-list',
  templateUrl: './accommodation-list.component.html',
  styleUrls: ['./accommodation-list.component.css']
})
export class AccommodationListComponent implements OnInit {

	accommodationUnits: any = [];

	constructor(private router: Router) { 

		let mockAu = {
			name: 'Wonderful Seaside Studio',
			description: 'You will not regret visiting one of the most beautiful places on planet earth - town of Perast. In our accommodation you will feel seaside sensation.',
			location: {
				city: {
					name: 'Perast',
					country: {
						name: 'Montenegro'
					}
				}
			},
			capacity: 2,
			ratingAvg: 8.9,
			price: 55,
			image: {
				imageUrl: 'https://www.i-escape.com/image/hotel/perast-boutique-apartment/overview/151352.jpg'
			},
			amenity: [
				{
					name: 'Wifi',
					faIcon: 'fa fa-wifi'
				},
				{
					name: 'Air Conditioning',
					faIcon: 'fa fa-snowflake-o'
				}

			]
			
		} 

		for (let i = 0; i < 5; i++) {
			this.accommodationUnits.push(mockAu);
		}
	}

	ngOnInit() {
	}

	onAddAccommodationUnit() {
		this.router.navigate(['accommodation/new']);
	}

}
