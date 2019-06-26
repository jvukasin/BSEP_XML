import { Component, OnInit, Input } from '@angular/core';
import * as moment from 'moment';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservation-card',
  templateUrl: './reservation-card.component.html',
  styleUrls: ['./reservation-card.component.css']
})
export class ReservationCardComponent implements OnInit {
	
	isMsgHovered: boolean = false;
	@Input() reservation;

	constructor(private reservationService: ReservationService) { }

	ngOnInit() {

		this.reservation.startDate = moment(this.reservation.startDate).format('LL');
		this.reservation.endDate = moment(this.reservation.endDate).format('LL');
		
	}

	onMarkSuccessful() {
		this.reservationService.setSuccessful(this.reservation.id).subscribe(
			payload => console.log(payload),
			error => {
				console.log(error.error.text);
				if (error.error.text === 'success') {
					this.reservation.successful = true;
				}
			}
		)
	}

	onMessageOver() {
		this.isMsgHovered = true;
	}
	
	onMessageOut() {
		this.isMsgHovered = false;
	}
	

}
