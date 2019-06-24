import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

	constructor(private reservationService: ReservationService) { }

	ngOnInit() {
	}

	onFetchReservations() {
		console.log('ping');
		this.reservationService.fetchReservations().subscribe(
			payload => console.log(payload),
			error => console.log(error)
		);

	}

}
