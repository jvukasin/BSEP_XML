import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import * as moment from 'moment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  reservationList: any;

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {

    this.reservationService.getUserReservations().subscribe(
      (data) => {
        this.reservationList = data;
        if(this.reservationList.length === 0){
          //korisnik nema napravljene rezervacije - swal ili u html ?
        }
      },
      (error) => { alert(error.message) }
    )
    
  }

  calculatePrice(startDate,endDate, price){
    var start = moment(startDate);
    var end = moment(endDate);
    return end.diff(start,'days') * price;
  }


}
