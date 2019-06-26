import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/services/reservation.service';
import * as moment from 'moment';

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
		this.isFetching = true;
		this.reservationService.fetchReservations().subscribe(
			(payload: any[]) => {
				this.reservations = payload.map(r => {
					if (r.usernameReservator.includes("*self:")) {
						r.isSelfReserved = true;
					}
					return r;
				});

				this.reservations = this.reservations.sort((r1: any, r2: any) => r2.endDate - r1.endDate);
				this.isFetching = false;
	
			},
			error => console.log(error)
		);

	}

}
