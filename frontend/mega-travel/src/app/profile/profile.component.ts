import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import * as moment from 'moment';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  reservationList: any;
  user: any = null;

  constructor(private reservationService: ReservationService, private userService: UserService) { }

  ngOnInit() {

    this.userService.getUser().subscribe(
      (data) => {
        this.user = data;
      }, (error) => {
        alert("No user loged in");
      }
    );

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

  canCanel(res) {
    let todDate = new Date();
    let tod = moment(todDate).format('YYYY-MM-DD');
    let start = moment(res.startDate).format('YYYY-MM-DD');
    let dayz = this.daydiff(this.parseDate(tod), this.parseDate(start));
    if(dayz >= res.accommodation.cancelPeriod) {
      return true;
    } else {
      return false;
    }
  }

  parseDate(str) {
    var mdy = str.split('-');
    let aa = new Date();
    let dd = new Date(mdy[0], mdy[1]-1, mdy[2]);
    return dd;
  }

  daydiff(first, second) {
    return Math.round((second-first)/(1000*60*60*24));
  }

  cancelReservation(name, id) {
    Swal.fire({
      title: 'Confirm cancellation',
      text: 'Do you wish to cancel your reservation at ' + name + '?',
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonText:
        'Confirm',
      cancelButtonText:
        'Cancel',
    }).then((result) => {
      if (result.value) {
        this.reservationService.cancelReservation(id).subscribe(
          (data) => {
            Swal.fire({
              type: 'success',
              title: 'Your reservation is canceled!',
              showConfirmButton: false,
              timer: 2000
            });
            let i = this.reservationList.findIndex(reservation => reservation.id === id);
              this.reservationList.splice(i, 1);
          }, (error) => {
          Swal.fire({
            type: 'error',
            title: 'Something went wrong! Your reservation cannot be canceled.',
            showConfirmButton: true
          });
        }
        );
      }
    });
  }


}
