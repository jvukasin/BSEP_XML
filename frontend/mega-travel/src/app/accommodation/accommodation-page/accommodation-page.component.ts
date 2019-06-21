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
      }, (error) => {
        Swal.fire({
          type: 'info',
          title: 'You need to be logged in to make a reservation.',
          showConfirmButton: false,
          timer: 2000
        });
        this.router.navigate(['login']);
      }
    );

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
      }, (error) => {
        Swal.fire({
          type: 'error',
          title: 'Something went wrong! Your reservation cannot be made.',
          showConfirmButton: true
        });
      }
    );
  }

}
