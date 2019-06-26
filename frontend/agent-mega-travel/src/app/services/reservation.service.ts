import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
	
	
	ENDPOINT_URI: string = "/api/reservations";
	
	constructor(private http: HttpClient) { }

	getOneReservation(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/" + id);
	}

	getMessages(id: number) {
		return this.http.get(this.ENDPOINT_URI + "/" + id + "/messages"); 
	}

	postMessage(id:number, message: any) {
		return this.http.post(this.ENDPOINT_URI + "/" + id + "/messages", message);
	}
	
	fetchReservations() {
		return this.http.get(this.ENDPOINT_URI);
	}

	postReservation(reservation: any) {
		return this.http.post(this.ENDPOINT_URI, reservation);
	}
	
	setSuccessful(id: any) {
		console.log('ping');
		return this.http.put(this.ENDPOINT_URI + "/" + id + "/success", {});
	}
}
