import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
	isFetching: boolean = false;
	reservations: any = null;

	constructor(private reservationService: ReservationService) { }

	ngOnInit() {

		this.onFetchReservations();
	}

	onFetchReservations() {
		console.log('ping');
		this.isFetching = true;
		this.reservationService.fetchReservations().subscribe(
			payload => {
				this.reservations = payload;
				this.isFetching = false;
			},
			error => console.log(error)
		);

	}

}
