import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { ReturnStatement } from '@angular/compiler';


@Injectable()
export class ReservationService {

    constructor(private http: HttpClient, private router: Router) { }

    makeReservation(res) {
        return this.http.post("/api/reservationservice/reservations", res);
    }

    getUserReservations(){
        return this.http.get('api/reservationservice/reservations/all');
    }

}