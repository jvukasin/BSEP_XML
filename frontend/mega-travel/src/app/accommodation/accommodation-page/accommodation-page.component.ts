import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../../services/accommodation.service';
import { SearchResultsService } from '../../services/search-results.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import Swal from 'sweetalert2';
import { ReservationService } from 'src/app/services/reservation.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-accommodation-page',
  templateUrl: './accommodation-page.component.html',
  styleUrls: ['./accommodation-page.component.css']
})
export class AccommodationPageComponent implements OnInit {

  hideServ: boolean = true;
  ShowText: any = "Show more";
  acu: any = null;
  id: number;
  sDate: Date;
  eDate: Date;
  guests: any;
  dayz: any = 0;
  amenities: any = [];
  firstIm: any;
  imagesRest: any = [];
  totalPrice: number;
  user: any;

  ratingText: String;
  ratingColor: {};

  constructor(private accService: AccommodationService, private srcService: SearchResultsService, private userService: UserService, private resService: ReservationService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
      }
  );
  }

  ngOnInit() {

    if(this.id !== NaN && this.id !== undefined) {
      this.accService.getAccUnit(this.id).subscribe(
        (data) => {
          this.acu = data;
          this.amenities = this.acu.amenities;
          this.parseRatingTextAndColor(this.acu.ratingAvg);
          this.firstIm = this.acu.images[0];
          this.imagesRest = this.acu.images.slice(1);
        },
        (error) => {
          Swal.fire({
            type: 'error',
            title: 'Could not find desireable accommodation unit.',
            showConfirmButton: false,
            timer: 1500
          });
          this.router.navigate(['home']);
        }
      );
    }

    this.sDate = this.srcService.startDate;
    this.eDate = this.srcService.endDate;
    this.guests = this.srcService.guests;
    this.totalPrice = this.srcService.totalPrice;

    if(this.sDate == undefined || this.eDate == undefined) {
      this.router.navigate(['home']);
    }
  }

  parseRatingTextAndColor(rating) {
    if(rating >= 9.0) {
      this.ratingText = "Excellent";
      this.ratingColor = { 'background-color': 'rgb(66, 94, 250)'};
    } else if (rating >= 8.0 && rating < 9.0) {
      this.ratingText = "Very good";
      this.ratingColor = { 'background-color': 'rgb(36, 163, 36)'};
    } else if (rating >= 7.0 && rating < 8.0) {
      this.ratingText = "Good";
      this.ratingColor = { 'background-color': 'rgb(180, 245, 30)'};
    } else if (rating >= 6.0 && rating < 7.0) {
      this.ratingText = "Satisfactory";
      this.ratingColor = { 'background-color': 'rgb(241, 245, 30)'};
    } else if (rating >= 5.0 && rating < 6.0) {
      this.ratingText = "Poor";
      this.ratingColor = { 'background-color': 'rgb(245, 206, 30)'};
    } else if (rating >= 4.0 && rating < 5.0) {
      this.ratingText = "Very Poor";
      this.ratingColor = { 'background-color': 'rgb(36, 163, 36)'};
    } else if (rating >= 3.0 && rating < 4.0) {
      this.ratingText = "Bad";
      this.ratingColor = { 'background-color': 'rgb(231, 115, 37)'};
    } else {
      this.ratingText = "Very Bad";
      this.ratingColor = { 'background-color': 'rgb(241, 33, 33)'};
    }
  }

  parseDate(str) {
    var mdy = str.split('-')
    let aa = new Date();
    let dd = new Date(mdy[0], mdy[1]-1, mdy[2]);
    return dd;
  }

  daydiff(first, second) {
    return Math.round((second-first)/(1000*60*60*24));
  }

  onShowMore() {
    this.hideServ = !this.hideServ;
    if(this.ShowText == "Show more") {
      this.ShowText = "Show less"
    } else {
      this.ShowText = "Show more"
    }
  }

  onMakeReservation() {
    this.userService.getUser().subscribe(
      (data) => {
        this.user = data;
        this.doRes();
      }, (error) => {
        Swal.fire({
          title: 'You need to be logged in to make a reservation.',
          type: 'info',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Log In now'
        }).then((result) => {
          if (result.value) {
            this.router.navigate(['login']);
          }
        })
      }
    );
  }

  doRes() {

    Swal.fire({
      title: 'Confirm reservation',
      text: 'Do you wish to confirm your reservation?',
      html:
        '<b>Accommodation:</b> ' + this.acu.name +
        '<br><b>From</b> ' + this.sDate + ' <b>to</b> ' + this.eDate +
        '<br><b>Cancellation period:</b> ' + this.acu.cancellationPeriod + ' days' +
        '<br><b>Total price:</b> $' + this.totalPrice,
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonText:
        'Confirm',
      cancelButtonText:
        'Cancel',
    }).then((result) => {
      if (result.value) {
        let res = {
          startDate: this.sDate,
          endDate: this.eDate,
          accommodationUnitId: this.id,
          price: this.totalPrice,
          reservator: this.user
        }
    
        this.resService.makeReservation(res).subscribe(
          (data) => {
            Swal.fire({
              type: 'success',
              title: 'Your reservation was successful!',
              showConfirmButton: false,
              timer: 2000
            });
            this.router.navigate(['profile']);
          }, (error) => {
            Swal.fire({
              type: 'error',
              title: 'Something went wrong! Your reservation cannot be made.',
              showConfirmButton: true
            });
          }
        );
      }
    })
  }

}
